cmake_minimum_required(VERSION 3.20)

project(cjson)
set(CMAKE_C_STANDARD 99)
include_directories(include)
add_library(${PROJECT_NAME} SHARED src/${PROJECT_NAME}.c)

include(CTest)
enable_testing()
add_subdirectory(test)