\RequirePackage{expl3}
\ProvidesExplClass{zlhywlfbook}{2022/12/12}{1.0.0}{book}
\LoadClass{ctexbook}

% N: 接收一个命令，传递命令本身
% V: 接收一个命令，传递命令的值
% n: 接收一个凭据表
% o: 接收一个凭据表，对凭据表的内容进行一次展开
% x: 接收一个凭据表，对凭据表的内容进行递归展开
% T/F: 接收一个凭据表，条件成立时执行
% c: 接收一个凭据表，返回以其为名字的命令
% p: 参数列表

\cs_set:Npn \factorial:n #1{
    \int_set:Nn \l_tmpa_int {1}
    \seq_clear:N \l_tmpa_seq
    \int_step_inline:nn {#1} {
        \seq_put_right:Nn \l_tmpa_seq {##1}
        \int_set:Nn \l_tmpa_int {\l_tmpa_int * ##1}
    }
    $ \seq_use:Nn \l_tmpa_seq {\times} = \int_use:N \l_tmpa_int $
}
