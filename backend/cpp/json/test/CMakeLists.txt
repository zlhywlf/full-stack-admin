link_libraries(${PROJECT_NAME} zlhywlf::root)

project(jsonTest)
add_executable(${PROJECT_NAME} ${PROJECT_NAME}.cc)
add_test(${PROJECT_NAME} ${PROJECT_NAME})