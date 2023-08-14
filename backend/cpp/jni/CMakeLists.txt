project(jni)
add_library(${PROJECT_NAME} INTERFACE)
add_library(zlhywlf::${PROJECT_NAME} ALIAS ${PROJECT_NAME})
target_link_libraries(${PROJECT_NAME} INTERFACE zlhywlf::root zlhywlf::json)
target_include_directories(${PROJECT_NAME} INTERFACE src ${PROJECT_BINARY_DIR} $ENV{JAVA_HOME}/include $ENV{JAVA_HOME}/include/linux $ENV{JAVA_HOME}/include/darwin)

configure_file("${PROJECT_SOURCE_DIR}/test/config.h.in" "${PROJECT_BINARY_DIR}/config.h")

if (CMAKE_BUILD_TYPE STREQUAL Debug)
    include(CTest)
    enable_testing()
    add_subdirectory(test)
endif()