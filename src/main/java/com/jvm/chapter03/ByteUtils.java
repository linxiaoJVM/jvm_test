package com.jvm.chapter03;

import java.util.Date;

public class ByteUtils {
	
	/**
	 * int到byte[]
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];   
        //由高位到低位
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF); 
        result[3] = (byte)(i & 0xFF);
        return result;
    }
    /**
     * byte[]转int
     * @param bytes
     * @return
     */
	public static int byteArrayToInt(byte[] bytes) {
        int value= 0;
        //由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift= (4 - 1 - i) * 8;
            value +=(bytes[i] & 0x000000FF) << shift;//往高位游
        }
        return value;
    }
	public static int byteArrayToInt2(byte[] bytes) {
		int value= 0;
		//由高位到低位
		for (int i = 0; i < 2; i++) {
			int shift= (2 - 1 - i) * 8;
			value +=(bytes[i] & 0x000000FF) << shift;//往高位游
		}
		return value;
	}
	/**
	 * int转成2个字节
	 * @param n
	 * @return
	 */
	public static byte[] ushortToBytes(int n) {
		
        byte[] b = new byte[2];
        b[0] = (byte) ((n >> 8) & 0xff);
        b[1] = (byte) ( n       & 0xff);
        return b;
	}
	
	/**
	 * mac地址转化为btye类型
	 * @param mac
	 * @return
	 */
	 public static byte [] getMacBytes(String mac){
		 byte []macBytes = new byte[6];
		 String [] strArr = mac.split(":");
	  
		 for(int i = 0;i < strArr.length; i++){
			 int value = Integer.parseInt(strArr[i],16);
			 macBytes[i] = (byte) value;
		 }
		 return macBytes;
	 }
	/**
	 * byte转化成mac地址
	 * @param macBytes
	 * @return
	 */
	public static String byteToMac(byte [] macBytes) {
		String value = "";
		for(int i = 0;i < macBytes.length; i++){
			String sTemp = Integer.toHexString(0xFF &  macBytes[i]);
			value = value+sTemp+":";
		}
		value = value.substring(0,value.lastIndexOf(":"));
		//System.out.println("value="+value);
		return value;
	}
	
	public static void main(String args[]) {
		byte [] aa = intToByteArray(3);
		System.out.println( intToByteArray(225) );
		//ushortToBytes(0);
		byte[] b = {0,3};
		System.out.println(byteArrayToInt2(b));
		
		byte [] macBytes =  getMacBytes("00:50:56:c0:00:01");
		String mac = byteToMac(macBytes);
		System.out.println(0x000000FF);
	}

}
