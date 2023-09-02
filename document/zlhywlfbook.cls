% 指定当前类必须使用的格式
\NeedsTeXFormat{LaTeX2e}

%  标识类
\ProvidesClass{zlhywlfbook}[2022/12/12 v1.0.0 zlhywlfbook document class]

% ====================================================
% 自定义选项
% ====================================================
\RequirePackage{kvoptions}

% 设置引用选项前缀
\SetupKeyvalOptions{family=BOOK, prefix=BOOK@}

% 处理选项
\ProcessKeyvalOptions*
\relax

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

% ====================================================
% 标题
% ====================================================
\RequirePackage{titlesec}
\setcounter{secnumdepth}{3}

% chapter
% {标题命令}[% 形状，hang 标志与内容在一行]{标题格式}{标志}{标志与内容间距}{内容}[后命令]
\titleformat{\chapter}[hang]{\sf\bfseries\huge\filcenter}{第\chinese{chapter}章}{1em}{}[]
% {标题命令}{左间距}{上间距}{下间距}[右间距]
\titlespacing{\chapter}{0em}{*3}{*6}[0em]

% section
\titleformat{\section}[hang]{\sf\Large\bfseries\filcenter}{\thesection}{1em}{}[]

% subsection
\titleformat{\subsection}[hang]{\sf\large\bfseries\filright}{\thesubsection}{1em}{}[]

% subsubsection
\titleformat{\subsubsection}[hang]{\sf\normalsize\bfseries\filright}{\thesubsubsection}{1em}{}[]

% part
\titleformat{\part}[display]{\sf\huge\bfseries\filcenter}{第\chinese{part}部分}{1ex}{}[]

% 汉化
\renewcommand\partname{部分}
\renewcommand\contentsname{目录}
\renewcommand\listfigurename{插图}
\renewcommand\listtablename{表格}
\renewcommand\figurename{图}
\renewcommand\tablename{表}
\renewcommand\abstractname{摘要}
\renewcommand\indexname{索引}
\renewcommand\appendixname{附录}
\renewcommand\bibname{参考文献}
\renewcommand\proofname{证明}

% 标题引用
\RequirePackage{nameref}
\renewcommand\Nameref[1]{\textbf{\ref{#1}~\nameref{#1}}}

% 标题脚注支持
\RequirePackage[stable]{footmisc}

% ====================================================
% 目录
% ====================================================
\RequirePackage{titletoc}
\setcounter{tocdepth}{2}

% part
% {标题名}[左间距]{标题格式}{标志}{无序号标题}{指引线与页码}[下间距]
\renewcommand\thepart{第\chinese{part}部分}
\titlecontents{part}[0em]{\sf\Large\bfseries\filcenter\addvspace{2ex}}{}{}{}[]

% chapter
\titlecontents{chapter}[0em]{\sf\large\bfseries\addvspace{2ex}}{第\zhnumber{\thecontentslabel}章\quad}{}{}[]

% section
\titlecontents{section}[1em]{\sf\addvspace{1ex}}{\thecontentslabel\quad}{}{\titlerule*[.7pc]{.}\contentspage}[]

% subsection
\titlecontents{subsection}[2em]{\sf\addvspace{1ex}}{\thecontentslabel\quad}{}{\titlerule*[.7pc]{.}\contentspage}

% ====================================================
% 页眉页脚
% ====================================================
\RequirePackage{fancyhdr}
\pagestyle{fancy}
\fancyhf{}
% EL EC ER | OL OC OR
\fancyhead[EL,OR]{--\ \thepage\ --}
\fancyhead[EC,OC]{\nouppercase{\fangsong\rightmark}}
% 标题页不要页眉页脚
\fancypagestyle{plain}{\renewcommand{\headrulewidth}{0em}\fancyhf{}}
% 将小节置于页眉
\renewcommand\sectionmark[1]{\markright{\thesection#1}}

% ====================================================
% 封面
% ====================================================
\RequirePackage{tikz}
\definecolor{coverC1}{RGB}{184,0,78}
\definecolor{coverC2}{RGB}{47,72,88}
\newcommand\drawCircle[3]{\draw[domain=-pi:pi,rotate=#1,color=#2,#3]plot({4*1.6*cos(\x/2 r)*cos(\x r)},{4*1.6*cos(\x/2 r)*sin(\x r)});}

\renewcommand*{\maketitle}{
    \pagestyle{empty}
    \begin{titlepage}
        \newgeometry{margin=0mm}\parindent=0mm
        {\null \vskip 15ex}
        \begin{center}
            \begin{tikzpicture}[font=\large]
                \draw[->,dashed,color=coverC2](-7.5,0)--(7.5,0)node[below]{$x$};
                \draw[->,dashed,color=coverC2](0,-7)--(0,7.5)node[left]{$y$};
                \drawCircle{0}{coverC2}{dashed,samples=200}
                \drawCircle{90}{coverC2}{dashed,samples=200}
                \drawCircle{180}{coverC2}{dashed,samples=200}
                \drawCircle{270}{coverC1}{ultra thick}
                \node at(0,-7.5){$R=a(1-\sin\theta)$};
            \end{tikzpicture}
        \end{center}
        {\vskip 15ex}
        {
        \begin{center}
            \ttfamily
            {\Huge \textbf{\@title} \par}
            {\vskip 4ex}
            {\large \lineskip .75ex
                \begin{tabular}[t]{c}
                    \@author
                \end{tabular}
            }
            {\vskip 2.5ex}
            {\large \@date \par}
            {\null\vfill}
            {\large\rm{\@saying}}
        \end{center}\par
        }
    \end{titlepage}
    \frontmatter
    \pagestyle{fancy}
    \tableofcontents
    \mainmatter
}
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