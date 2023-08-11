link_libraries(${PROJECT_NAME} jvm)
link_directories($ENV{JAVA_HOME}/lib/server)
include_directories(${PROJECT_BINARY_DIR})

configure_file("${PROJECT_SOURCE_DIR}/test/config.h.in" "${PROJECT_BINARY_DIR}/config.h")

project(jniTest)
add_executable(${PROJECT_NAME} ${PROJECT_NAME}.cc)
add_library(${PROJECT_NAME}_1 SHARED ${PROJECT_NAME}.C)
add_library(${PROJECT_NAME}_2 SHARED ${PROJECT_NAME}.cpp)
add_test(${PROJECT_NAME} ${PROJECT_NAME})