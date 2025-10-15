package com.google.crypto.tink.subtle;

import java.util.Arrays;
import kotlin.UByte;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Field25519.smali */
final class Field25519 {
    static final int FIELD_LEN = 32;
    static final int LIMB_CNT = 10;
    private static final long TWO_TO_25 = 33554432;
    private static final long TWO_TO_26 = 67108864;
    private static final int[] EXPAND_START = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    private static final int[] EXPAND_SHIFT = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] MASK = {67108863, 33554431};
    private static final int[] SHIFT = {26, 25};

    Field25519() {
    }

    static void sum(long[] output, long[] in1, long[] in2) {
        for (int i = 0; i < 10; i++) {
            output[i] = in1[i] + in2[i];
        }
    }

    static void sum(long[] output, long[] in) {
        sum(output, output, in);
    }

    static void sub(long[] output, long[] in1, long[] in2) {
        for (int i = 0; i < 10; i++) {
            output[i] = in1[i] - in2[i];
        }
    }

    static void sub(long[] output, long[] in) {
        sub(output, in, output);
    }

    static void scalarProduct(long[] output, long[] in, long scalar) {
        for (int i = 0; i < 10; i++) {
            output[i] = in[i] * scalar;
        }
    }

    static void product(long[] out, long[] in2, long[] in) {
        out[0] = in2[0] * in[0];
        out[1] = (in2[0] * in[1]) + (in2[1] * in[0]);
        out[2] = (in2[1] * 2 * in[1]) + (in2[0] * in[2]) + (in2[2] * in[0]);
        out[3] = (in2[1] * in[2]) + (in2[2] * in[1]) + (in2[0] * in[3]) + (in2[3] * in[0]);
        out[4] = (in2[2] * in[2]) + (((in2[1] * in[3]) + (in2[3] * in[1])) * 2) + (in2[0] * in[4]) + (in2[4] * in[0]);
        out[5] = (in2[2] * in[3]) + (in2[3] * in[2]) + (in2[1] * in[4]) + (in2[4] * in[1]) + (in2[0] * in[5]) + (in2[5] * in[0]);
        out[6] = (((in2[3] * in[3]) + (in2[1] * in[5]) + (in2[5] * in[1])) * 2) + (in2[2] * in[4]) + (in2[4] * in[2]) + (in2[0] * in[6]) + (in2[6] * in[0]);
        out[7] = (in2[3] * in[4]) + (in2[4] * in[3]) + (in2[2] * in[5]) + (in2[5] * in[2]) + (in2[1] * in[6]) + (in2[6] * in[1]) + (in2[0] * in[7]) + (in2[7] * in[0]);
        out[8] = (in2[4] * in[4]) + (((in2[3] * in[5]) + (in2[5] * in[3]) + (in2[1] * in[7]) + (in2[7] * in[1])) * 2) + (in2[2] * in[6]) + (in2[6] * in[2]) + (in2[0] * in[8]) + (in2[8] * in[0]);
        out[9] = (in2[4] * in[5]) + (in2[5] * in[4]) + (in2[3] * in[6]) + (in2[6] * in[3]) + (in2[2] * in[7]) + (in2[7] * in[2]) + (in2[1] * in[8]) + (in2[8] * in[1]) + (in2[0] * in[9]) + (in2[9] * in[0]);
        out[10] = (((in2[5] * in[5]) + (in2[3] * in[7]) + (in2[7] * in[3]) + (in2[1] * in[9]) + (in2[9] * in[1])) * 2) + (in2[4] * in[6]) + (in2[6] * in[4]) + (in2[2] * in[8]) + (in2[8] * in[2]);
        out[11] = (in2[5] * in[6]) + (in2[6] * in[5]) + (in2[4] * in[7]) + (in2[7] * in[4]) + (in2[3] * in[8]) + (in2[8] * in[3]) + (in2[2] * in[9]) + (in2[9] * in[2]);
        out[12] = (in2[6] * in[6]) + (((in2[5] * in[7]) + (in2[7] * in[5]) + (in2[3] * in[9]) + (in2[9] * in[3])) * 2) + (in2[4] * in[8]) + (in2[8] * in[4]);
        out[13] = (in2[6] * in[7]) + (in2[7] * in[6]) + (in2[5] * in[8]) + (in2[8] * in[5]) + (in2[4] * in[9]) + (in2[9] * in[4]);
        out[14] = (((in2[7] * in[7]) + (in2[5] * in[9]) + (in2[9] * in[5])) * 2) + (in2[6] * in[8]) + (in2[8] * in[6]);
        out[15] = (in2[7] * in[8]) + (in2[8] * in[7]) + (in2[6] * in[9]) + (in2[9] * in[6]);
        out[16] = (in2[8] * in[8]) + (((in2[7] * in[9]) + (in2[9] * in[7])) * 2);
        out[17] = (in2[8] * in[9]) + (in2[9] * in[8]);
        out[18] = in2[9] * 2 * in[9];
    }

    static void reduce(long[] input, long[] output) {
        long[] tmp;
        if (input.length == 19) {
            tmp = input;
        } else {
            tmp = new long[19];
            System.arraycopy(input, 0, tmp, 0, input.length);
        }
        reduceSizeByModularReduction(tmp);
        reduceCoefficients(tmp);
        System.arraycopy(tmp, 0, output, 0, 10);
    }

    static void reduceSizeByModularReduction(long[] output) {
        output[8] = output[8] + (output[18] << 4);
        output[8] = output[8] + (output[18] << 1);
        output[8] = output[8] + output[18];
        output[7] = output[7] + (output[17] << 4);
        output[7] = output[7] + (output[17] << 1);
        output[7] = output[7] + output[17];
        output[6] = output[6] + (output[16] << 4);
        output[6] = output[6] + (output[16] << 1);
        output[6] = output[6] + output[16];
        output[5] = output[5] + (output[15] << 4);
        output[5] = output[5] + (output[15] << 1);
        output[5] = output[5] + output[15];
        output[4] = output[4] + (output[14] << 4);
        output[4] = output[4] + (output[14] << 1);
        output[4] = output[4] + output[14];
        output[3] = output[3] + (output[13] << 4);
        output[3] = output[3] + (output[13] << 1);
        output[3] = output[3] + output[13];
        output[2] = output[2] + (output[12] << 4);
        output[2] = output[2] + (output[12] << 1);
        output[2] = output[2] + output[12];
        output[1] = output[1] + (output[11] << 4);
        output[1] = output[1] + (output[11] << 1);
        output[1] = output[1] + output[11];
        output[0] = output[0] + (output[10] << 4);
        output[0] = output[0] + (output[10] << 1);
        output[0] = output[0] + output[10];
    }

    static void reduceCoefficients(long[] output) {
        output[10] = 0;
        for (int i = 0; i < 10; i += 2) {
            long over = output[i] / TWO_TO_26;
            output[i] = output[i] - (over << 26);
            int i2 = i + 1;
            output[i2] = output[i2] + over;
            long over2 = output[i + 1] / TWO_TO_25;
            int i3 = i + 1;
            output[i3] = output[i3] - (over2 << 25);
            int i4 = i + 2;
            output[i4] = output[i4] + over2;
        }
        output[0] = output[0] + (output[10] << 4);
        output[0] = output[0] + (output[10] << 1);
        output[0] = output[0] + output[10];
        output[10] = 0;
        long over3 = output[0] / TWO_TO_26;
        output[0] = output[0] - (over3 << 26);
        output[1] = output[1] + over3;
    }

    static void mult(long[] output, long[] in, long[] in2) {
        long[] t = new long[19];
        product(t, in, in2);
        reduce(t, output);
    }

    private static void squareInner(long[] out, long[] in) {
        out[0] = in[0] * in[0];
        out[1] = in[0] * 2 * in[1];
        out[2] = ((in[1] * in[1]) + (in[0] * in[2])) * 2;
        out[3] = ((in[1] * in[2]) + (in[0] * in[3])) * 2;
        out[4] = (in[2] * in[2]) + (in[1] * 4 * in[3]) + (in[0] * 2 * in[4]);
        out[5] = ((in[2] * in[3]) + (in[1] * in[4]) + (in[0] * in[5])) * 2;
        out[6] = ((in[3] * in[3]) + (in[2] * in[4]) + (in[0] * in[6]) + (in[1] * 2 * in[5])) * 2;
        out[7] = ((in[3] * in[4]) + (in[2] * in[5]) + (in[1] * in[6]) + (in[0] * in[7])) * 2;
        out[8] = (in[4] * in[4]) + (((in[2] * in[6]) + (in[0] * in[8]) + (((in[1] * in[7]) + (in[3] * in[5])) * 2)) * 2);
        out[9] = ((in[4] * in[5]) + (in[3] * in[6]) + (in[2] * in[7]) + (in[1] * in[8]) + (in[0] * in[9])) * 2;
        out[10] = ((in[5] * in[5]) + (in[4] * in[6]) + (in[2] * in[8]) + (((in[3] * in[7]) + (in[1] * in[9])) * 2)) * 2;
        out[11] = ((in[5] * in[6]) + (in[4] * in[7]) + (in[3] * in[8]) + (in[2] * in[9])) * 2;
        out[12] = (in[6] * in[6]) + (((in[4] * in[8]) + (((in[5] * in[7]) + (in[3] * in[9])) * 2)) * 2);
        out[13] = ((in[6] * in[7]) + (in[5] * in[8]) + (in[4] * in[9])) * 2;
        out[14] = ((in[7] * in[7]) + (in[6] * in[8]) + (in[5] * 2 * in[9])) * 2;
        out[15] = ((in[7] * in[8]) + (in[6] * in[9])) * 2;
        out[16] = (in[8] * in[8]) + (in[7] * 4 * in[9]);
        out[17] = in[8] * 2 * in[9];
        out[18] = in[9] * 2 * in[9];
    }

    static void square(long[] output, long[] in) {
        long[] t = new long[19];
        squareInner(t, in);
        reduce(t, output);
    }

    static long[] expand(byte[] input) {
        long[] output = new long[10];
        for (int i = 0; i < 10; i++) {
            int[] iArr = EXPAND_START;
            output[i] = (((((input[iArr[i]] & UByte.MAX_VALUE) | ((input[iArr[i] + 1] & UByte.MAX_VALUE) << 8)) | ((input[iArr[i] + 2] & UByte.MAX_VALUE) << 16)) | ((input[iArr[i] + 3] & UByte.MAX_VALUE) << 24)) >> EXPAND_SHIFT[i]) & MASK[i & 1];
        }
        return output;
    }

    static byte[] contract(long[] inputLimbs) {
        long[] input = Arrays.copyOf(inputLimbs, 10);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 9; i++) {
                int carry = -((int) ((input[i] & (input[i] >> 31)) >> SHIFT[i & 1]));
                input[i] = input[i] + (carry << r11[i & 1]);
                int i2 = i + 1;
                input[i2] = input[i2] - carry;
            }
            int carry2 = -((int) (((input[9] >> 31) & input[9]) >> 25));
            input[9] = input[9] + (carry2 << 25);
            input[0] = input[0] - (carry2 * 19);
        }
        int carry3 = -((int) ((input[0] & (input[0] >> 31)) >> 26));
        input[0] = input[0] + (carry3 << 26);
        input[1] = input[1] - carry3;
        for (int j2 = 0; j2 < 2; j2++) {
            for (int i3 = 0; i3 < 9; i3++) {
                int carry4 = (int) (input[i3] >> SHIFT[i3 & 1]);
                input[i3] = input[i3] & MASK[i3 & 1];
                int i4 = i3 + 1;
                input[i4] = input[i4] + carry4;
            }
        }
        int carry5 = (int) (input[9] >> 25);
        input[9] = input[9] & 33554431;
        input[0] = input[0] + (carry5 * 19);
        int mask = gte((int) input[0], 67108845);
        for (int i5 = 1; i5 < 10; i5++) {
            mask &= eq((int) input[i5], MASK[i5 & 1]);
        }
        input[0] = input[0] - (67108845 & mask);
        input[1] = input[1] - (mask & 33554431);
        for (int i6 = 2; i6 < 10; i6 += 2) {
            input[i6] = input[i6] - (67108863 & mask);
            int i7 = i6 + 1;
            input[i7] = input[i7] - (mask & 33554431);
        }
        for (int i8 = 0; i8 < 10; i8++) {
            input[i8] = input[i8] << EXPAND_SHIFT[i8];
        }
        byte[] output = new byte[32];
        for (int i9 = 0; i9 < 10; i9++) {
            int[] iArr = EXPAND_START;
            output[iArr[i9]] = (byte) (output[r9] | (input[i9] & 255));
            output[iArr[i9] + 1] = (byte) (output[r9] | ((input[i9] >> 8) & 255));
            output[iArr[i9] + 2] = (byte) (output[r9] | ((input[i9] >> 16) & 255));
            output[iArr[i9] + 3] = (byte) (output[r8] | ((input[i9] >> 24) & 255));
        }
        return output;
    }

    static void inverse(long[] out, long[] z) {
        long[] z2 = new long[10];
        long[] z9 = new long[10];
        long[] z11 = new long[10];
        long[] z2To5Minus1 = new long[10];
        long[] z2To10Minus1 = new long[10];
        long[] z2To20Minus1 = new long[10];
        long[] z2To50Minus1 = new long[10];
        long[] z2To100Minus1 = new long[10];
        long[] t0 = new long[10];
        long[] t1 = new long[10];
        square(z2, z);
        square(t1, z2);
        square(t0, t1);
        mult(z9, t0, z);
        mult(z11, z9, z2);
        square(t0, z11);
        mult(z2To5Minus1, t0, z9);
        square(t0, z2To5Minus1);
        square(t1, t0);
        square(t0, t1);
        square(t1, t0);
        square(t0, t1);
        mult(z2To10Minus1, t0, z2To5Minus1);
        square(t0, z2To10Minus1);
        square(t1, t0);
        for (int i = 2; i < 10; i += 2) {
            square(t0, t1);
            square(t1, t0);
        }
        mult(z2To20Minus1, t1, z2To10Minus1);
        square(t0, z2To20Minus1);
        square(t1, t0);
        for (int i2 = 2; i2 < 20; i2 += 2) {
            square(t0, t1);
            square(t1, t0);
        }
        mult(t0, t1, z2To20Minus1);
        square(t1, t0);
        square(t0, t1);
        for (int i3 = 2; i3 < 10; i3 += 2) {
            square(t1, t0);
            square(t0, t1);
        }
        mult(z2To50Minus1, t0, z2To10Minus1);
        square(t0, z2To50Minus1);
        square(t1, t0);
        for (int i4 = 2; i4 < 50; i4 += 2) {
            square(t0, t1);
            square(t1, t0);
        }
        mult(z2To100Minus1, t1, z2To50Minus1);
        square(t1, z2To100Minus1);
        square(t0, t1);
        for (int i5 = 2; i5 < 100; i5 += 2) {
            square(t1, t0);
            square(t0, t1);
        }
        mult(t1, t0, z2To100Minus1);
        square(t0, t1);
        square(t1, t0);
        for (int i6 = 2; i6 < 50; i6 += 2) {
            square(t0, t1);
            square(t1, t0);
        }
        mult(t0, t1, z2To50Minus1);
        square(t1, t0);
        square(t0, t1);
        square(t1, t0);
        square(t0, t1);
        square(t1, t0);
        mult(out, t1, z11);
    }

    private static int eq(int a, int b) {
        int a2 = ~(a ^ b);
        int a3 = a2 & (a2 << 16);
        int a4 = a3 & (a3 << 8);
        int a5 = a4 & (a4 << 4);
        int a6 = a5 & (a5 << 2);
        return (a6 & (a6 << 1)) >> 31;
    }

    private static int gte(int a, int b) {
        return ~((a - b) >> 31);
    }
}
