#include "MyJni.h"

JNIEXPORT jint JNICALL Java_pnrs_vezbe_projekat_11_MyNDK_increment
  (JNIEnv *env, jobject obj, jint x){
  return ++x;
  }

JNIEXPORT jdouble JNICALL
Java_pnrs_vezbe_projekat_11_MyNDK_convertDegrees(JNIEnv *env, jobject instance, jdouble d,
                                                      jint t) {

    // TODO
    if (t == 0){
        d=d* 9/5 - 459.67;
        return d;
    }else{
        d = d -273.15;
        return d;
    }

}

