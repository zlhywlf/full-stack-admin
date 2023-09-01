% 指定当前类必须使用的格式
\NeedsTeXFormat{LaTeX2e}

%  标识类
\ProvidesClass{zlhywlfbook}[2022/12/12 v1.0.0 zlhywlfbook document class]

% 选项
\input{config/option}

% 加载类
\LoadClass[a4paper,oneside]{book}

% 工具
\RequirePackage{etoolbox}

% 图
\RequirePackage{graphicx}
\graphicspath{{./asset/}}

% 颜色
\RequirePackage[table]{xcolor}

% 绘图
\RequirePackage{tikz}

% 超链接
\RequirePackage[hidelinks]{hyperref}

% 页面
\RequirePackage[
    a4paper,
    top=25.4mm, bottom=25.4mm,
    left=20mm, right=20mm,
    headheight=2.17cm,
    headsep=4mm,
    footskip=12mm
]{geometry}

% 字体
\input{config/font}

% 封面
\input{config/cover}

% 目录
\input{config/content}

% 标题
\input{config/title}

% 页眉页脚
\RequirePackage{fancyhdr}
\fancyhf{}
\pagestyle{fancy}
\fancyfoot[c]{\small\thepage}
\fancyhead[R]{\rightmark}

% 代码块 \definecolor{winered}{rgb}{0.5,0,0}
\RequirePackage{listings}
\definecolor{dkgreen}{rgb}{0,0.6,0}
\definecolor{winered}{rgb}{0.5,0,0}
\lstset{
    frame=tb,
    aboveskip=3mm,
    belowskip=3mm,
    showstringspaces=false,
    columns=flexible,
    basicstyle = \ttfamily\small,
    keywordstyle=\color{winered},
    commentstyle=\color{dkgreen},
    stringstyle=\color{dkgreen},
    breaklines=true,
    breakatwhitespace=true,
    tabsize=2
}