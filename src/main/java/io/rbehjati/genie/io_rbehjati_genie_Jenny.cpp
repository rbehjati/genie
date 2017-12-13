#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "jenny.c"
#include "io_rbehjati_genie_Jenny.h"


JNIEXPORT jobjectArray JNICALL Java_io_rbehjati_genie_Jenny_generator
  (JNIEnv * env, jobject thisObj, jobjectArray params){

    int stringCount = 1 + env->GetArrayLength(params);
    char** rawString = (char**)malloc(stringCount * sizeof(char*));

    rawString[0] = (char *) malloc(strlen("main"));
    strcpy(rawString[0], "main");
    for (int i=1; i<stringCount; i++) {
        jstring string = (jstring) env->GetObjectArrayElement(params, i-1);
        const char * str = env->GetStringUTFChars(string, 0);
        rawString[i] = (char *) malloc(strlen(str));
        strcpy(rawString[i], str);
    }

    Output result = generator(stringCount, rawString);
      
    jclass stringClass = env->FindClass("java/lang/String");
    jobjectArray row = env->NewObjectArray(result.length, stringClass, 0);

    for (int i = 0; i < result.length; ++i) {
        env->SetObjectArrayElement(row, i, env->NewStringUTF(result.tests[i]));
    }

    // cleanup
    for (int i = 1; i < stringCount; i++) {
    	jstring string = (jstring) env->GetObjectArrayElement(params, i-1);
    	env->ReleaseStringUTFChars(string, rawString[i]);
    }
    free(rawString);

	return row;
}




