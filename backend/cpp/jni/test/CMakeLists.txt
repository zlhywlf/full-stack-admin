link_libraries(${PROJECT_NAME} jvm)
link_directories($ENV{JAVA_HOME}/lib/server)

project(jniTest)
add_executable(${PROJECT_NAME} ${PROJECT_NAME}.cc)
add_test(${PROJECT_NAME} ${PROJECT_NAME})