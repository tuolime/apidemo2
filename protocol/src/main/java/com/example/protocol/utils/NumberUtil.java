 package com.example.protocol.utils;


 import com.example.protocol.exception.InvalidNumericConvertException;

 import java.math.BigDecimal;

 public class NumberUtil
 {
   public static byte byte2BCD(byte binValue)
   {
     return (byte)(binValue % 10 + (binValue / 10 << 4));
   }
 
   public static byte[] intTo2Bin(int d)
   {
     byte[] b = new byte[2];
     b[0] = ((byte)((short)d & 0xFF));
     b[1] = ((byte)((short)d >> 8));
     return b;
   }
 
   public static byte[] lngTo8Bin(long d)
   {
     byte[] b = new byte[8];
     b[0] = ((byte)(int)(d & 0xFF));
     b[1] = ((byte)(int)(d >> 8 & 0xFF));
     b[2] = ((byte)(int)(d >> 16 & 0xFF));
     b[3] = ((byte)(int)(d >> 24 & 0xFF));
     b[4] = ((byte)(int)(d >> 32 & 0xFF));
     b[5] = ((byte)(int)(d >> 40 & 0xFF));
     b[6] = ((byte)(int)(d >> 48 & 0xFF));
     b[7] = ((byte)(int)(d >> 56 & 0xFF));
     return b;
   }
 
   public static byte[] lngTo6Bin(long d) {
     byte[] b = new byte[6];
     b[0] = ((byte)(int)(d & 0xFF));
     b[1] = ((byte)(int)(d >> 8 & 0xFF));
     b[2] = ((byte)(int)(d >> 16 & 0xFF));
     b[3] = ((byte)(int)(d >> 24 & 0xFF));
     b[4] = ((byte)(int)(d >> 32 & 0xFF));
     b[5] = ((byte)(int)(d >> 40 & 0xFF));
     return b;
   }
 
   public static byte[] lngTo4Bin(long d) {
     byte[] b = new byte[4];
     b[0] = ((byte)(int)(d & 0xFF));
     b[1] = ((byte)(int)(d >> 8 & 0xFF));
     b[2] = ((byte)(int)(d >> 16 & 0xFF));
     b[3] = ((byte)(int)(d >> 24 & 0xFF));
 
     return b;
   }
 
   public static byte[] intTo4Bin(int d) {
     byte[] b = new byte[4];
     b[0] = ((byte)(d & 0xFF));
     b[1] = ((byte)(d >> 8 & 0xFF));
     b[2] = ((byte)(d >> 16 & 0xFF));
     b[3] = ((byte)(d >> 24 & 0xFF));
     return b;
   }
 
   public static int fromBcd(byte bcdCode)
   {
     byte hi = (byte)(bcdCode >> 4 & 0xF);
     byte low = (byte)(bcdCode & 0xF);
     if ((hi > 9) || (low > 9))
       return -1;
     return hi * 10 + low;
   }
 
   public static int from4BitsBcd(byte bcdCode)
   {
     if ((bcdCode & 0xF) > 9)
       return -1;
     return bcdCode & 0xF;
   }
 
   public static int fromBcd(int bcdCode)
   {
     if (bcdCode == -1)
       throw new InvalidNumericConvertException();
     byte data1Low = (byte)(bcdCode & 0xF);
     byte data1Hi = (byte)(bcdCode >> 4 & 0xF);
     byte data2Low = (byte)(bcdCode >> 8 & 0xF);
     byte data2Hi = (byte)(bcdCode >> 12 & 0xF);
     byte data3Low = (byte)(bcdCode >> 16 & 0xF);
     byte data3Hi = (byte)(bcdCode >> 20 & 0xF);
     byte data4Low = (byte)(bcdCode >> 24 & 0xF);
     byte data4Hi = (byte)(bcdCode >> 28 & 0xF);
     if ((data1Low > 9) || (data1Hi > 9) || (data2Low > 9) || (data2Hi > 9) || (data3Low > 9) || (data3Hi > 9) || (data4Low > 9) || 
       (data4Hi > 9)) {
       return -1;
     }
     return data1Low + data1Hi * 10 + data2Low * 100 + data2Hi * 1000 + data3Low * 10000 + data3Hi * 100000 + 
       data4Low * 1000000 + data4Hi * 10000000;
   }
 
   public static int fromBcd(short bcdCode)
   {
     byte data1Low = (byte)(bcdCode & 0xF);
     byte data1Hi = (byte)(bcdCode >> 4 & 0xF);
     byte data2Low = (byte)(bcdCode >> 8 & 0xF);
     byte data2Hi = (byte)(bcdCode >> 12 & 0xF);
     if ((data1Low > 9) || (data1Hi > 9) || (data2Low > 9) || (data2Hi > 9))
       return -1;
     return data1Low + data1Hi * 10 + data2Low * 100 + data2Hi * 1000;
   }
 
   public static int fromHi3BitsBcd(byte bcdCode)
   {
     return bcdCode >> 4 & 0x7;
   }
 
   public static int parse2BytesBin(byte[] frame, int offset)
   {
     int a = frame[offset] < 0 ? frame[offset] + 256 : frame[offset];
     int b = frame[(offset + 1)] < 0 ? frame[(offset + 1)] + 256 : frame[(offset + 1)];
     return a + (b << 8);
   }
 
   public static long parse4BytesBin(byte[] frame, int offset)
   {
     int a = frame[offset] < 0 ? frame[offset] + 256 : frame[offset];
     int b = frame[(offset + 1)] < 0 ? frame[(offset + 1)] + 256 : frame[(offset + 1)];
     int c = frame[(offset + 2)] < 0 ? frame[(offset + 2)] + 256 : frame[(offset + 2)];
     int d = frame[(offset + 3)] < 0 ? frame[(offset + 3)] + 256 : frame[(offset + 3)];
     return a | b << 8 | c << 16 | d << 24;
   }
 
   public static long parse6BytesBin(byte[] frame, int offset)
   {
     long a = frame[offset] < 0 ? frame[offset] + 256 : frame[offset];
     long b = frame[(offset + 1)] < 0 ? frame[(offset + 1)] + 256 : frame[(offset + 1)];
     long c = frame[(offset + 2)] < 0 ? frame[(offset + 2)] + 256 : frame[(offset + 2)];
     long d = frame[(offset + 3)] < 0 ? frame[(offset + 3)] + 256 : frame[(offset + 3)];
     long e = frame[(offset + 4)] < 0 ? frame[(offset + 4)] + 256 : frame[(offset + 4)];
     long f = frame[(offset + 5)] < 0 ? frame[(offset + 5)] + 256 : frame[(offset + 5)];
     return a | b << 8 | c << 16 | d << 24 | e << 32 | f << 40;
   }
 
   public static long parse8BytesBin(byte[] frame, int offset)
   {
     long a = frame[offset] < 0 ? frame[offset] + 256 : frame[offset];
     long b = frame[(offset + 1)] < 0 ? frame[(offset + 1)] + 256 : frame[(offset + 1)];
     long c = frame[(offset + 2)] < 0 ? frame[(offset + 2)] + 256 : frame[(offset + 2)];
     long d = frame[(offset + 3)] < 0 ? frame[(offset + 3)] + 256 : frame[(offset + 3)];
     long e = frame[(offset + 4)] < 0 ? frame[(offset + 4)] + 256 : frame[(offset + 4)];
     long f = frame[(offset + 5)] < 0 ? frame[(offset + 5)] + 256 : frame[(offset + 5)];
     long g = frame[(offset + 6)] < 0 ? frame[(offset + 6)] + 256 : frame[(offset + 6)];
     long h = frame[(offset + 7)] < 0 ? frame[(offset + 7)] + 256 : frame[(offset + 7)];
     return a | b << 8 | c << 16 | d << 24 | e << 32 | f << 40 | g << 48 | h << 56;
   }
 
   public static int parse3BytesBin(byte[] frame, int offset)
   {
     int a = frame[offset] < 0 ? frame[offset] + 256 : frame[offset];
     int b = frame[(offset + 1)] < 0 ? frame[(offset + 1)] + 256 : frame[(offset + 1)];
     int c = frame[(offset + 2)] < 0 ? frame[(offset + 2)] + 256 : frame[(offset + 2)];
     return a | b << 8 | c << 16;
   }
 
   public static double formatDigitNumber(double num)
   {
     BigDecimal b = new BigDecimal(num);
     return b.setScale(2, 4).doubleValue();
   }
 
   public static double formatNDigitNumber(double num, int n)
   {
     BigDecimal b = new BigDecimal(num);
     return b.setScale(Math.abs(n), 4).doubleValue();
   }
 }

/* 
 * Qualified Name:     com.tties.protocol.util.NumberUtil
 * 
 */