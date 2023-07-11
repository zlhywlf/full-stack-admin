link_libraries(${PROJECT_NAME})

project(${PROJECT_NAME}Test)
add_executable(${PROJECT_NAME} ${PROJECT_NAME}.c)
add_test(${PROJECT_NAME} ${PROJECT_NAME})