% 指定当前类必须使用的格式
\NeedsTeXFormat{LaTeX2e}

%  标识类
\ProvidesClass{zlhywlfbook}[2022/12/12 v1.0.0 zlhywlfbook document class]

% 工具
\RequirePackage{etoolbox}

% 选项
\input{config/option}

% 加载类
\LoadClass[a4paper,oneside]{book}

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

% 超链接
\RequirePackage[hidelinks]{hyperref}

% 页眉页脚
\RequirePackage{fancyhdr}
\pagestyle{fancy}
\fancyhf{}
\rhead{\textnormal{\rightmark}}
\fancyfoot[C]{\thepage}

% 代码块
\RequirePackage{listings}
\RequirePackage{color}
\RequirePackage{xcolor}
\definecolor{dkgreen}{rgb}{0,0.6,0}
\definecolor{gray}{rgb}{0.5,0.5,0.5}
\definecolor{mauve}{rgb}{0.58,0,0.82}
\lstset{
    numbers=left,
    frame=tb,
    aboveskip=3mm,
    belowskip=3mm,
    showstringspaces=false,
    columns=flexible,
    basicstyle = \ttfamily\small,
    numberstyle=\tiny\color{gray},
    keywordstyle=\color{blue},
    commentstyle=\color{dkgreen},
    stringstyle=\color{mauve},
    breaklines=true,
    breakatwhitespace=true,
    tabsize=2
}