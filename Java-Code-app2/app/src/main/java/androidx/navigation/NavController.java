package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavController.smali */
public class NavController {
    private static final String KEY_BACK_STACK = "android-support-nav:controller:backStack";
    static final String KEY_DEEP_LINK_EXTRAS = "android-support-nav:controller:deepLinkExtras";
    static final String KEY_DEEP_LINK_HANDLED = "android-support-nav:controller:deepLinkHandled";
    static final String KEY_DEEP_LINK_IDS = "android-support-nav:controller:deepLinkIds";
    public static final String KEY_DEEP_LINK_INTENT = "android-support-nav:controller:deepLinkIntent";
    private static final String KEY_NAVIGATOR_STATE = "android-support-nav:controller:navigatorState";
    private static final String KEY_NAVIGATOR_STATE_NAMES = "android-support-nav:controller:navigatorState:names";
    private static final String TAG = "NavController";
    private Activity mActivity;
    private Parcelable[] mBackStackToRestore;
    private final Context mContext;
    private boolean mDeepLinkHandled;
    NavGraph mGraph;
    private NavInflater mInflater;
    private LifecycleOwner mLifecycleOwner;
    private Bundle mNavigatorStateToRestore;
    private NavControllerViewModel mViewModel;
    final Deque<NavBackStackEntry> mBackStack = new ArrayDeque();
    private NavigatorProvider mNavigatorProvider = new NavigatorProvider();
    private final CopyOnWriteArrayList<OnDestinationChangedListener> mOnDestinationChangedListeners = new CopyOnWriteArrayList<>();
    private final LifecycleObserver mLifecycleObserver = new LifecycleEventObserver() { // from class: androidx.navigation.NavController.1
        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
            if (NavController.this.mGraph != null) {
                for (NavBackStackEntry entry : NavController.this.mBackStack) {
                    entry.handleLifecycleEvent(event);
                }
            }
        }
    };
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) { // from class: androidx.navigation.NavController.2
        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            NavController.this.popBackStack();
        }
    };
    private boolean mEnableOnBackPressedCallback = true;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavController$OnDestinationChangedListener.smali */
    public interface OnDestinationChangedListener {
        void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle);
    }

    public NavController(Context context) {
        this.mContext = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            }
            if (context instanceof Activity) {
                this.mActivity = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        NavigatorProvider navigatorProvider = this.mNavigatorProvider;
        navigatorProvider.addNavigator(new NavGraphNavigator(navigatorProvider));
        this.mNavigatorProvider.addNavigator(new ActivityNavigator(this.mContext));
    }

    public Deque<NavBackStackEntry> getBackStack() {
        return this.mBackStack;
    }

    Context getContext() {
        return this.mContext;
    }

    public NavigatorProvider getNavigatorProvider() {
        return this.mNavigatorProvider;
    }

    public void setNavigatorProvider(NavigatorProvider navigatorProvider) {
        if (!this.mBackStack.isEmpty()) {
            throw new IllegalStateException("NavigatorProvider must be set before setGraph call");
        }
        this.mNavigatorProvider = navigatorProvider;
    }

    public void addOnDestinationChangedListener(OnDestinationChangedListener listener) {
        if (!this.mBackStack.isEmpty()) {
            NavBackStackEntry backStackEntry = this.mBackStack.peekLast();
            listener.onDestinationChanged(this, backStackEntry.getDestination(), backStackEntry.getArguments());
        }
        this.mOnDestinationChangedListeners.add(listener);
    }

    public void removeOnDestinationChangedListener(OnDestinationChangedListener listener) {
        this.mOnDestinationChangedListeners.remove(listener);
    }

    public boolean popBackStack() {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        return popBackStack(getCurrentDestination().getId(), true);
    }

    public boolean popBackStack(int destinationId, boolean inclusive) {
        boolean popped = popBackStackInternal(destinationId, inclusive);
        return popped && dispatchOnDestinationChanged();
    }

    boolean popBackStackInternal(int destinationId, boolean inclusive) {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        ArrayList<Navigator<?>> popOperations = new ArrayList<>();
        Iterator<NavBackStackEntry> iterator = this.mBackStack.descendingIterator();
        boolean foundDestination = false;
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }
            NavDestination destination = iterator.next().getDestination();
            Navigator<?> navigator = this.mNavigatorProvider.getNavigator(destination.getNavigatorName());
            if (inclusive || destination.getId() != destinationId) {
                popOperations.add(navigator);
            }
            if (destination.getId() == destinationId) {
                foundDestination = true;
                break;
            }
        }
        if (!foundDestination) {
            String destinationName = NavDestination.getDisplayName(this.mContext, destinationId);
            Log.i(TAG, "Ignoring popBackStack to destination " + destinationName + " as it was not found on the current back stack");
            return false;
        }
        boolean popped = false;
        Iterator<Navigator<?>> it = popOperations.iterator();
        while (it.hasNext()) {
            Navigator<?> navigator2 = it.next();
            if (!navigator2.popBackStack()) {
                break;
            }
            NavBackStackEntry entry = this.mBackStack.removeLast();
            if (entry.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                entry.setMaxLifecycle(Lifecycle.State.DESTROYED);
            }
            NavControllerViewModel navControllerViewModel = this.mViewModel;
            if (navControllerViewModel != null) {
                navControllerViewModel.clear(entry.mId);
            }
            popped = true;
        }
        updateOnBackPressedCallbackEnabled();
        return popped;
    }

    public boolean navigateUp() throws PackageManager.NameNotFoundException {
        if (getDestinationCountOnBackStack() == 1) {
            NavDestination currentDestination = getCurrentDestination();
            int destId = currentDestination.getId();
            for (NavGraph parent = currentDestination.getParent(); parent != null; parent = parent.getParent()) {
                if (parent.getStartDestination() != destId) {
                    Bundle args = new Bundle();
                    Activity activity = this.mActivity;
                    if (activity != null && activity.getIntent() != null) {
                        Uri data = this.mActivity.getIntent().getData();
                        if (data != null) {
                            args.putParcelable(KEY_DEEP_LINK_INTENT, this.mActivity.getIntent());
                            NavDestination.DeepLinkMatch matchingDeepLink = this.mGraph.matchDeepLink(new NavDeepLinkRequest(this.mActivity.getIntent()));
                            if (matchingDeepLink != null) {
                                Bundle destinationArgs = matchingDeepLink.getDestination().addInDefaultArgs(matchingDeepLink.getMatchingArgs());
                                args.putAll(destinationArgs);
                            }
                        }
                    }
                    TaskStackBuilder parentIntents = new NavDeepLinkBuilder(this).setDestination(parent.getId()).setArguments(args).createTaskStackBuilder();
                    parentIntents.startActivities();
                    Activity activity2 = this.mActivity;
                    if (activity2 != null) {
                        activity2.finish();
                    }
                    return true;
                }
                destId = parent.getId();
            }
            return false;
        }
        return popBackStack();
    }

    private int getDestinationCountOnBackStack() {
        int count = 0;
        for (NavBackStackEntry entry : this.mBackStack) {
            if (!(entry.getDestination() instanceof NavGraph)) {
                count++;
            }
        }
        return count;
    }

    private boolean dispatchOnDestinationChanged() {
        while (!this.mBackStack.isEmpty() && (this.mBackStack.peekLast().getDestination() instanceof NavGraph) && popBackStackInternal(this.mBackStack.peekLast().getDestination().getId(), true)) {
        }
        if (!this.mBackStack.isEmpty()) {
            NavDestination nextResumed = this.mBackStack.peekLast().getDestination();
            NavDestination nextStarted = null;
            if (nextResumed instanceof FloatingWindow) {
                Iterator<NavBackStackEntry> iterator = this.mBackStack.descendingIterator();
                while (true) {
                    if (!iterator.hasNext()) {
                        break;
                    }
                    NavDestination destination = iterator.next().getDestination();
                    if (!(destination instanceof NavGraph) && !(destination instanceof FloatingWindow)) {
                        nextStarted = destination;
                        break;
                    }
                }
            }
            HashMap<NavBackStackEntry, Lifecycle.State> upwardStateTransitions = new HashMap<>();
            Iterator<NavBackStackEntry> iterator2 = this.mBackStack.descendingIterator();
            while (iterator2.hasNext()) {
                NavBackStackEntry entry = iterator2.next();
                Lifecycle.State currentMaxLifecycle = entry.getMaxLifecycle();
                NavDestination destination2 = entry.getDestination();
                if (nextResumed != null && destination2.getId() == nextResumed.getId()) {
                    if (currentMaxLifecycle != Lifecycle.State.RESUMED) {
                        upwardStateTransitions.put(entry, Lifecycle.State.RESUMED);
                    }
                    nextResumed = nextResumed.getParent();
                } else if (nextStarted != null && destination2.getId() == nextStarted.getId()) {
                    if (currentMaxLifecycle == Lifecycle.State.RESUMED) {
                        entry.setMaxLifecycle(Lifecycle.State.STARTED);
                    } else if (currentMaxLifecycle != Lifecycle.State.STARTED) {
                        upwardStateTransitions.put(entry, Lifecycle.State.STARTED);
                    }
                    nextStarted = nextStarted.getParent();
                } else {
                    entry.setMaxLifecycle(Lifecycle.State.CREATED);
                }
            }
            for (NavBackStackEntry entry2 : this.mBackStack) {
                Lifecycle.State newState = upwardStateTransitions.get(entry2);
                if (newState != null) {
                    entry2.setMaxLifecycle(newState);
                } else {
                    entry2.updateState();
                }
            }
            NavBackStackEntry backStackEntry = this.mBackStack.peekLast();
            Iterator<OnDestinationChangedListener> it = this.mOnDestinationChangedListeners.iterator();
            while (it.hasNext()) {
                OnDestinationChangedListener listener = it.next();
                listener.onDestinationChanged(this, backStackEntry.getDestination(), backStackEntry.getArguments());
            }
            return true;
        }
        return false;
    }

    public NavInflater getNavInflater() {
        if (this.mInflater == null) {
            this.mInflater = new NavInflater(this.mContext, this.mNavigatorProvider);
        }
        return this.mInflater;
    }

    public void setGraph(int graphResId) {
        setGraph(graphResId, (Bundle) null);
    }

    public void setGraph(int graphResId, Bundle startDestinationArgs) {
        setGraph(getNavInflater().inflate(graphResId), startDestinationArgs);
    }

    public void setGraph(NavGraph graph) {
        setGraph(graph, (Bundle) null);
    }

    public void setGraph(NavGraph graph, Bundle startDestinationArgs) {
        NavGraph navGraph = this.mGraph;
        if (navGraph != null) {
            popBackStackInternal(navGraph.getId(), true);
        }
        this.mGraph = graph;
        onGraphCreated(startDestinationArgs);
    }

    private void onGraphCreated(Bundle startDestinationArgs) {
        Activity activity;
        ArrayList<String> navigatorNames;
        Bundle bundle = this.mNavigatorStateToRestore;
        if (bundle != null && (navigatorNames = bundle.getStringArrayList(KEY_NAVIGATOR_STATE_NAMES)) != null) {
            Iterator<String> it = navigatorNames.iterator();
            while (it.hasNext()) {
                String name = it.next();
                Navigator<?> navigator = this.mNavigatorProvider.getNavigator(name);
                Bundle bundle2 = this.mNavigatorStateToRestore.getBundle(name);
                if (bundle2 != null) {
                    navigator.onRestoreState(bundle2);
                }
            }
        }
        Parcelable[] parcelableArr = this.mBackStackToRestore;
        boolean z = false;
        if (parcelableArr != null) {
            for (Parcelable parcelable : parcelableArr) {
                NavBackStackEntryState state = (NavBackStackEntryState) parcelable;
                NavDestination node = findDestination(state.getDestinationId());
                if (node == null) {
                    String dest = NavDestination.getDisplayName(this.mContext, state.getDestinationId());
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + dest + " cannot be found from the current destination " + getCurrentDestination());
                }
                Bundle args = state.getArgs();
                if (args != null) {
                    args.setClassLoader(this.mContext.getClassLoader());
                }
                NavBackStackEntry entry = new NavBackStackEntry(this.mContext, node, args, this.mLifecycleOwner, this.mViewModel, state.getUUID(), state.getSavedState());
                this.mBackStack.add(entry);
            }
            updateOnBackPressedCallbackEnabled();
            this.mBackStackToRestore = null;
        }
        if (this.mGraph != null && this.mBackStack.isEmpty()) {
            if (!this.mDeepLinkHandled && (activity = this.mActivity) != null && handleDeepLink(activity.getIntent())) {
                z = true;
            }
            boolean deepLinked = z;
            if (!deepLinked) {
                navigate(this.mGraph, startDestinationArgs, (NavOptions) null, (Navigator.Extras) null);
                return;
            }
            return;
        }
        dispatchOnDestinationChanged();
    }

    public boolean handleDeepLink(Intent intent) throws PackageManager.NameNotFoundException {
        NavDestination.DeepLinkMatch matchingDeepLink;
        Object obj;
        NavGraph graph;
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        int[] deepLink = extras != null ? extras.getIntArray(KEY_DEEP_LINK_IDS) : null;
        Bundle bundle = new Bundle();
        Bundle deepLinkExtras = extras != null ? extras.getBundle(KEY_DEEP_LINK_EXTRAS) : null;
        if (deepLinkExtras != null) {
            bundle.putAll(deepLinkExtras);
        }
        if ((deepLink == null || deepLink.length == 0) && intent.getData() != null && (matchingDeepLink = this.mGraph.matchDeepLink(new NavDeepLinkRequest(intent))) != null) {
            NavDestination destination = matchingDeepLink.getDestination();
            deepLink = destination.buildDeepLinkIds();
            Bundle destinationArgs = destination.addInDefaultArgs(matchingDeepLink.getMatchingArgs());
            bundle.putAll(destinationArgs);
        }
        if (deepLink == null || deepLink.length == 0) {
            return false;
        }
        String invalidDestinationDisplayName = findInvalidDestinationDisplayNameInDeepLink(deepLink);
        if (invalidDestinationDisplayName != null) {
            Log.i(TAG, "Could not find destination " + invalidDestinationDisplayName + " in the navigation graph, ignoring the deep link from " + intent);
            return false;
        }
        bundle.putParcelable(KEY_DEEP_LINK_INTENT, intent);
        int flags = intent.getFlags();
        int i = 1;
        if ((flags & 268435456) != 0 && (flags & 32768) == 0) {
            intent.addFlags(32768);
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.mContext).addNextIntentWithParentStack(intent);
            taskStackBuilder.startActivities();
            Activity activity = this.mActivity;
            if (activity != null) {
                activity.finish();
                this.mActivity.overridePendingTransition(0, 0);
            }
            return true;
        }
        if ((268435456 & flags) != 0) {
            if (!this.mBackStack.isEmpty()) {
                popBackStackInternal(this.mGraph.getId(), true);
            }
            int destinationId = 0;
            while (destinationId < deepLink.length) {
                int index = destinationId + 1;
                int destinationId2 = deepLink[destinationId];
                NavDestination node = findDestination(destinationId2);
                if (node == null) {
                    String dest = NavDestination.getDisplayName(this.mContext, destinationId2);
                    throw new IllegalStateException("Deep Linking failed: destination " + dest + " cannot be found from the current destination " + getCurrentDestination());
                }
                navigate(node, bundle, new NavOptions.Builder().setEnterAnim(0).setExitAnim(0).build(), (Navigator.Extras) null);
                destinationId = index;
            }
            return true;
        }
        NavGraph graph2 = this.mGraph;
        int i2 = 0;
        while (i2 < deepLink.length) {
            int destinationId3 = deepLink[i2];
            NavDestination node2 = i2 == 0 ? this.mGraph : graph2.findNode(destinationId3);
            if (node2 == null) {
                String dest2 = NavDestination.getDisplayName(this.mContext, destinationId3);
                throw new IllegalStateException("Deep Linking failed: destination " + dest2 + " cannot be found in graph " + graph2);
            }
            if (i2 != deepLink.length - i) {
                NavDestination navDestinationFindNode = node2;
                while (true) {
                    graph = (NavGraph) navDestinationFindNode;
                    if (!(graph.findNode(graph.getStartDestination()) instanceof NavGraph)) {
                        break;
                    }
                    navDestinationFindNode = graph.findNode(graph.getStartDestination());
                }
                graph2 = graph;
                obj = null;
            } else {
                obj = null;
                navigate(node2, node2.addInDefaultArgs(bundle), new NavOptions.Builder().setPopUpTo(this.mGraph.getId(), true).setEnterAnim(0).setExitAnim(0).build(), (Navigator.Extras) null);
            }
            i2++;
            i = 1;
        }
        this.mDeepLinkHandled = true;
        return true;
    }

    private String findInvalidDestinationDisplayNameInDeepLink(int[] deepLink) {
        NavGraph graph = this.mGraph;
        int i = 0;
        while (true) {
            NavDestination node = null;
            if (i >= deepLink.length) {
                return null;
            }
            int destinationId = deepLink[i];
            if (i == 0) {
                if (this.mGraph.getId() == destinationId) {
                    node = this.mGraph;
                }
            } else {
                node = graph.findNode(destinationId);
            }
            if (node == null) {
                return NavDestination.getDisplayName(this.mContext, destinationId);
            }
            if (i != deepLink.length - 1) {
                NavDestination navDestinationFindNode = node;
                while (true) {
                    graph = (NavGraph) navDestinationFindNode;
                    if (graph.findNode(graph.getStartDestination()) instanceof NavGraph) {
                        navDestinationFindNode = graph.findNode(graph.getStartDestination());
                    }
                }
            }
            i++;
        }
    }

    public NavGraph getGraph() {
        NavGraph navGraph = this.mGraph;
        if (navGraph == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()");
        }
        return navGraph;
    }

    public NavDestination getCurrentDestination() {
        NavBackStackEntry entry = getCurrentBackStackEntry();
        if (entry != null) {
            return entry.getDestination();
        }
        return null;
    }

    NavDestination findDestination(int destinationId) {
        NavDestination currentNode;
        NavGraph currentGraph;
        NavGraph navGraph = this.mGraph;
        if (navGraph == null) {
            return null;
        }
        if (navGraph.getId() == destinationId) {
            return this.mGraph;
        }
        if (this.mBackStack.isEmpty()) {
            currentNode = this.mGraph;
        } else {
            currentNode = this.mBackStack.getLast().getDestination();
        }
        if (currentNode instanceof NavGraph) {
            currentGraph = (NavGraph) currentNode;
        } else {
            currentGraph = currentNode.getParent();
        }
        return currentGraph.findNode(destinationId);
    }

    public void navigate(int resId) {
        navigate(resId, (Bundle) null);
    }

    public void navigate(int resId, Bundle args) {
        navigate(resId, args, (NavOptions) null);
    }

    public void navigate(int resId, Bundle args, NavOptions navOptions) {
        navigate(resId, args, navOptions, (Navigator.Extras) null);
    }

    public void navigate(int resId, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        NavDestination currentNode;
        if (this.mBackStack.isEmpty()) {
            currentNode = this.mGraph;
        } else {
            currentNode = this.mBackStack.getLast().getDestination();
        }
        if (currentNode == null) {
            throw new IllegalStateException("no current navigation node");
        }
        int destId = resId;
        NavAction navAction = currentNode.getAction(resId);
        Bundle combinedArgs = null;
        if (navAction != null) {
            if (navOptions == null) {
                navOptions = navAction.getNavOptions();
            }
            destId = navAction.getDestinationId();
            Bundle navActionArgs = navAction.getDefaultArguments();
            if (navActionArgs != null) {
                combinedArgs = new Bundle();
                combinedArgs.putAll(navActionArgs);
            }
        }
        if (args != null) {
            if (combinedArgs == null) {
                combinedArgs = new Bundle();
            }
            combinedArgs.putAll(args);
        }
        if (destId == 0 && navOptions != null && navOptions.getPopUpTo() != -1) {
            popBackStack(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
            return;
        }
        if (destId == 0) {
            throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
        }
        NavDestination node = findDestination(destId);
        if (node == null) {
            String dest = NavDestination.getDisplayName(this.mContext, destId);
            if (navAction == null) {
                throw new IllegalArgumentException("Navigation action/destination " + dest + " cannot be found from the current destination " + currentNode);
            }
            throw new IllegalArgumentException("Navigation destination " + dest + " referenced from action " + NavDestination.getDisplayName(this.mContext, resId) + " cannot be found from the current destination " + currentNode);
        }
        navigate(node, combinedArgs, navOptions, navigatorExtras);
    }

    public void navigate(Uri deepLink) {
        navigate(new NavDeepLinkRequest(deepLink, null, null));
    }

    public void navigate(Uri deepLink, NavOptions navOptions) {
        navigate(new NavDeepLinkRequest(deepLink, null, null), navOptions);
    }

    public void navigate(Uri deepLink, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        navigate(new NavDeepLinkRequest(deepLink, null, null), navOptions, navigatorExtras);
    }

    public void navigate(NavDeepLinkRequest request) {
        navigate(request, (NavOptions) null);
    }

    public void navigate(NavDeepLinkRequest request, NavOptions navOptions) {
        navigate(request, navOptions, (Navigator.Extras) null);
    }

    public void navigate(NavDeepLinkRequest request, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        NavDestination.DeepLinkMatch deepLinkMatch = this.mGraph.matchDeepLink(request);
        if (deepLinkMatch != null) {
            NavDestination destination = deepLinkMatch.getDestination();
            Bundle args = destination.addInDefaultArgs(deepLinkMatch.getMatchingArgs());
            if (args == null) {
                args = new Bundle();
            }
            NavDestination node = deepLinkMatch.getDestination();
            Intent intent = new Intent();
            intent.setDataAndType(request.getUri(), request.getMimeType());
            intent.setAction(request.getAction());
            args.putParcelable(KEY_DEEP_LINK_INTENT, intent);
            navigate(node, args, navOptions, navigatorExtras);
            return;
        }
        throw new IllegalArgumentException("Navigation destination that matches request " + request + " cannot be found in the navigation graph " + this.mGraph);
    }

    private void navigate(NavDestination node, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        boolean launchSingleTop;
        boolean launchSingleTop2;
        ArrayDeque<NavBackStackEntry> hierarchy;
        NavDestination destination;
        NavDestination destination2;
        NavGraph parent;
        boolean popped = false;
        boolean launchSingleTop3 = false;
        if (navOptions != null && navOptions.getPopUpTo() != -1) {
            popped = popBackStackInternal(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
        }
        Navigator<NavDestination> navigator = this.mNavigatorProvider.getNavigator(node.getNavigatorName());
        Bundle finalArgs = node.addInDefaultArgs(args);
        NavDestination newDest = navigator.navigate(node, finalArgs, navOptions, navigatorExtras);
        if (newDest != null) {
            if (!(newDest instanceof FloatingWindow)) {
                while (!this.mBackStack.isEmpty() && (this.mBackStack.peekLast().getDestination() instanceof FloatingWindow) && popBackStackInternal(this.mBackStack.peekLast().getDestination().getId(), true)) {
                }
            }
            ArrayDeque<NavBackStackEntry> hierarchy2 = new ArrayDeque<>();
            if (node instanceof NavGraph) {
                NavDestination destination3 = newDest;
                while (true) {
                    NavGraph parent2 = destination3.getParent();
                    if (parent2 == null) {
                        launchSingleTop = launchSingleTop3;
                        parent = parent2;
                        hierarchy = hierarchy2;
                    } else {
                        parent = parent2;
                        launchSingleTop = launchSingleTop3;
                        hierarchy = hierarchy2;
                        NavBackStackEntry entry = new NavBackStackEntry(this.mContext, parent2, finalArgs, this.mLifecycleOwner, this.mViewModel);
                        hierarchy.addFirst(entry);
                        if (!this.mBackStack.isEmpty() && this.mBackStack.getLast().getDestination() == parent) {
                            popBackStackInternal(parent.getId(), true);
                        }
                    }
                    NavGraph destination4 = parent;
                    if (destination4 == null || destination4 == node) {
                        break;
                    }
                    hierarchy2 = hierarchy;
                    destination3 = destination4;
                    launchSingleTop3 = launchSingleTop;
                }
            } else {
                launchSingleTop = false;
                hierarchy = hierarchy2;
            }
            if (hierarchy.isEmpty()) {
                destination = newDest;
            } else {
                destination = hierarchy.getFirst().getDestination();
            }
            NavDestination destination5 = destination;
            NavDestination destination6 = destination5;
            while (destination6 != null && findDestination(destination6.getId()) == null) {
                NavGraph parent3 = destination6.getParent();
                if (parent3 != null) {
                    NavBackStackEntry entry2 = new NavBackStackEntry(this.mContext, parent3, finalArgs, this.mLifecycleOwner, this.mViewModel);
                    hierarchy.addFirst(entry2);
                }
                destination6 = parent3;
            }
            if (hierarchy.isEmpty()) {
                destination2 = newDest;
            } else {
                destination2 = hierarchy.getLast().getDestination();
            }
            NavDestination overlappingDestination = destination2;
            while (!this.mBackStack.isEmpty() && (this.mBackStack.getLast().getDestination() instanceof NavGraph) && ((NavGraph) this.mBackStack.getLast().getDestination()).findNode(overlappingDestination.getId(), false) == null && popBackStackInternal(this.mBackStack.getLast().getDestination().getId(), true)) {
            }
            this.mBackStack.addAll(hierarchy);
            if (this.mBackStack.isEmpty() || this.mBackStack.getFirst().getDestination() != this.mGraph) {
                NavBackStackEntry entry3 = new NavBackStackEntry(this.mContext, this.mGraph, finalArgs, this.mLifecycleOwner, this.mViewModel);
                this.mBackStack.addFirst(entry3);
            }
            NavBackStackEntry newBackStackEntry = new NavBackStackEntry(this.mContext, newDest, newDest.addInDefaultArgs(finalArgs), this.mLifecycleOwner, this.mViewModel);
            this.mBackStack.add(newBackStackEntry);
        } else {
            launchSingleTop = false;
            if (navOptions != null && navOptions.shouldLaunchSingleTop()) {
                launchSingleTop2 = true;
                NavBackStackEntry singleTopBackStackEntry = this.mBackStack.peekLast();
                if (singleTopBackStackEntry != null) {
                    singleTopBackStackEntry.replaceArguments(finalArgs);
                }
            }
            updateOnBackPressedCallbackEnabled();
            if (!popped || newDest != null || launchSingleTop2) {
                dispatchOnDestinationChanged();
            }
            return;
        }
        launchSingleTop2 = launchSingleTop;
        updateOnBackPressedCallbackEnabled();
        if (!popped) {
        }
        dispatchOnDestinationChanged();
    }

    public void navigate(NavDirections directions) {
        navigate(directions.getActionId(), directions.getArguments());
    }

    public void navigate(NavDirections directions, NavOptions navOptions) {
        navigate(directions.getActionId(), directions.getArguments(), navOptions);
    }

    public void navigate(NavDirections directions, Navigator.Extras navigatorExtras) {
        navigate(directions.getActionId(), directions.getArguments(), (NavOptions) null, navigatorExtras);
    }

    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }

    public Bundle saveState() {
        Bundle b = null;
        ArrayList<String> navigatorNames = new ArrayList<>();
        Bundle navigatorState = new Bundle();
        for (Map.Entry<String, Navigator<? extends NavDestination>> entry : this.mNavigatorProvider.getNavigators().entrySet()) {
            String name = entry.getKey();
            Bundle savedState = entry.getValue().onSaveState();
            if (savedState != null) {
                navigatorNames.add(name);
                navigatorState.putBundle(name, savedState);
            }
        }
        if (!navigatorNames.isEmpty()) {
            b = new Bundle();
            navigatorState.putStringArrayList(KEY_NAVIGATOR_STATE_NAMES, navigatorNames);
            b.putBundle(KEY_NAVIGATOR_STATE, navigatorState);
        }
        if (!this.mBackStack.isEmpty()) {
            if (b == null) {
                b = new Bundle();
            }
            Parcelable[] backStack = new Parcelable[this.mBackStack.size()];
            int index = 0;
            for (NavBackStackEntry backStackEntry : this.mBackStack) {
                backStack[index] = new NavBackStackEntryState(backStackEntry);
                index++;
            }
            b.putParcelableArray(KEY_BACK_STACK, backStack);
        }
        if (this.mDeepLinkHandled) {
            if (b == null) {
                b = new Bundle();
            }
            b.putBoolean(KEY_DEEP_LINK_HANDLED, this.mDeepLinkHandled);
        }
        return b;
    }

    public void restoreState(Bundle navState) {
        if (navState == null) {
            return;
        }
        navState.setClassLoader(this.mContext.getClassLoader());
        this.mNavigatorStateToRestore = navState.getBundle(KEY_NAVIGATOR_STATE);
        this.mBackStackToRestore = navState.getParcelableArray(KEY_BACK_STACK);
        this.mDeepLinkHandled = navState.getBoolean(KEY_DEEP_LINK_HANDLED);
    }

    void setLifecycleOwner(LifecycleOwner owner) {
        if (owner == this.mLifecycleOwner) {
            return;
        }
        this.mLifecycleOwner = owner;
        owner.getLifecycle().addObserver(this.mLifecycleObserver);
    }

    void setOnBackPressedDispatcher(OnBackPressedDispatcher dispatcher) {
        if (this.mLifecycleOwner == null) {
            throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()");
        }
        this.mOnBackPressedCallback.remove();
        dispatcher.addCallback(this.mLifecycleOwner, this.mOnBackPressedCallback);
        this.mLifecycleOwner.getLifecycle().removeObserver(this.mLifecycleObserver);
        this.mLifecycleOwner.getLifecycle().addObserver(this.mLifecycleObserver);
    }

    void enableOnBackPressed(boolean enabled) {
        this.mEnableOnBackPressedCallback = enabled;
        updateOnBackPressedCallbackEnabled();
    }

    private void updateOnBackPressedCallbackEnabled() {
        this.mOnBackPressedCallback.setEnabled(this.mEnableOnBackPressedCallback && getDestinationCountOnBackStack() > 1);
    }

    void setViewModelStore(ViewModelStore viewModelStore) {
        if (this.mViewModel == NavControllerViewModel.getInstance(viewModelStore)) {
            return;
        }
        if (!this.mBackStack.isEmpty()) {
            throw new IllegalStateException("ViewModelStore should be set before setGraph call");
        }
        this.mViewModel = NavControllerViewModel.getInstance(viewModelStore);
    }

    public ViewModelStoreOwner getViewModelStoreOwner(int navGraphId) {
        if (this.mViewModel == null) {
            throw new IllegalStateException("You must call setViewModelStore() before calling getViewModelStoreOwner().");
        }
        NavBackStackEntry lastFromBackStack = getBackStackEntry(navGraphId);
        if (!(lastFromBackStack.getDestination() instanceof NavGraph)) {
            throw new IllegalArgumentException("No NavGraph with ID " + navGraphId + " is on the NavController's back stack");
        }
        return lastFromBackStack;
    }

    public NavBackStackEntry getBackStackEntry(int destinationId) {
        NavBackStackEntry lastFromBackStack = null;
        Iterator<NavBackStackEntry> iterator = this.mBackStack.descendingIterator();
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }
            NavBackStackEntry entry = iterator.next();
            NavDestination destination = entry.getDestination();
            if (destination.getId() == destinationId) {
                lastFromBackStack = entry;
                break;
            }
        }
        if (lastFromBackStack == null) {
            throw new IllegalArgumentException("No destination with ID " + destinationId + " is on the NavController's back stack. The current destination is " + getCurrentDestination());
        }
        return lastFromBackStack;
    }

    public NavBackStackEntry getCurrentBackStackEntry() {
        if (this.mBackStack.isEmpty()) {
            return null;
        }
        return this.mBackStack.getLast();
    }

    public NavBackStackEntry getPreviousBackStackEntry() {
        Iterator<NavBackStackEntry> iterator = this.mBackStack.descendingIterator();
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            NavBackStackEntry entry = iterator.next();
            if (!(entry.getDestination() instanceof NavGraph)) {
                return entry;
            }
        }
        return null;
    }
}
