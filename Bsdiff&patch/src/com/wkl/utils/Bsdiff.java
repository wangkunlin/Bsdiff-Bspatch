package com.wkl.utils;

public class Bsdiff {
	static {
		System.loadLibrary("bsdiffjni");
	}

	/**
	 * 文件二进制差分
	 * @param oldPath 旧文件路径
	 * @param newPath 新文件路径
	 * @param patchPath 生成的差分包路径
	 * @return
	 */
	public static native int bsdiff(String oldPath, String newPath,
			String patchPath);

	/**
	 * 文件合成
	 * @param oldPath 旧文件路径
	 * @param newPath 新生成的文件路径
	 * @param patchPath 差分包路径
	 * @return
	 */
	public static native int bspatch(String oldPath, String newPath,
			String patchPath);
}
