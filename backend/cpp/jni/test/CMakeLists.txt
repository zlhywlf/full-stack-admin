link_libraries(${PROJECT_NAME} jvm)
link_directories($ENV{JAVA_HOME}/lib/server)

project(jniTest)
add_executable(${PROJECT_NAME} ${PROJECT_NAME}.cc)
add_library(${PROJECT_NAME}_1 SHARED ${PROJECT_NAME}.C)
add_library(${PROJECT_NAME}_2 SHARED ${PROJECT_NAME}.cpp)
add_test(${PROJECT_NAME} ${PROJECT_NAME})