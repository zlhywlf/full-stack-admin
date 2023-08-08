link_libraries(${PROJECT_NAME})

project(rootTest)
add_executable(${PROJECT_NAME} ${PROJECT_NAME}.cc)
add_test(${PROJECT_NAME} ${PROJECT_NAME})
set_property(TEST ${PROJECT_NAME} PROPERTY PASS_REGULAR_EXPRESSION "hello world!")