// ICompareIntAidlInterface.aidl
package hdu.edu.cn.lessiontest6;

// Declare any non-default types here with import statements

interface ICompareIntAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    int intCompare(int n1,int n2);
}
