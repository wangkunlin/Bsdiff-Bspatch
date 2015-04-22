LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := bsdiffjni
LOCAL_C_INCLUDES := $(LOCAL_PATH)
LOCAL_SRC_FILES := \
			com_wkl_utils_patch.c \
			com_wkl_utils_diff.c
LOCAL_EXPORT_C_INCLUDES := ./bzip2
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
include $(BUILD_SHARED_LIBRARY)