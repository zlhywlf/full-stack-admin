% 指定当前类必须使用的格式
\NeedsTeXFormat{LaTeX2e}

%  标识类
\ProvidesClass{zlhywlfbook}[2022/12/12 v1.0.0 zlhywlfbook document class]

% 选项
\input{config/option}

% 加载类
% openany 避免空白页，例如：封面下一页是空白
\LoadClass[openany]{book}

% 页面
\RequirePackage[
    a4paper,
    top=25.4mm,bottom=25.4mm,
    left=20mm,right=20mm,
    headheight=2.17cm,
    headsep=4mm,
    footskip=12mm
]{geometry}

% 超链接
\RequirePackage[hidelinks]{hyperref}

% 中文支持
\RequirePackage[
    scheme=plain,
    fontset=zlhywlf,
    zihao=5
]{ctex}
\renewcommand{\baselinestretch}{1.3}

% 首段缩进
\RequirePackage{indentfirst}

% 使空白页无内容
\usepackage{emptypage}

% 标题
\input{config/title}

% 目录
\input{config/content}

% 页眉页脚
\input{config/header}

% 封面
\input{config/cover}
\newcommand\saying[1]{\gdef\@saying{#1}}

% 代码块
\RequirePackage{listings}
\definecolor{dkgreen}{rgb}{0,0.6,0}
\definecolor{winered}{rgb}{0.5,0,0}
\lstset{
    frame=tb,
    aboveskip=3mm,
    belowskip=3mm,
    showstringspaces=false,
    columns=flexible,
    basicstyle=\ttfamily\small,
    keywordstyle=\color{winered},
    commentstyle=\color{dkgreen},
    stringstyle=\color{dkgreen},
    breaklines=true,
    breakatwhitespace=true,
    tabsize=2
}