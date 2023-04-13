# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("incrementalupdate")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#定义一个全局变量包含了所有要编译的C文件
file(GLOB BZIP bzip/*.c)
#导入头文件
include_directories(bzip)
add_library( # Sets the name of the library.
        incrementalupdate
        SHARED
        incrementalupdate.cpp
        bspatch.c
        #将bzip下的.c文件添加到library
        BZIP)
find_library( # Sets the name of the path variable.
        log-lib
        log)
target_link_libraries( # Specifies the target library.
        incrementalupdate
        ${log-lib})