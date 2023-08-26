% 指定当前类必须使用的格式
\NeedsTeXFormat{LaTeX2e}
%  标识类
\ProvidesClass{zlhywlfbook}[2022/12/12 v1.0.0 zlhywlfbook document class]
% 加载类
\LoadClass{ctexrep}

% 页面大小
\RequirePackage[
    a4paper,
    top=25.4mm, bottom=25.4mm,
    left=20mm, right=20mm,
    headheight=2.17cm,
    headsep=4mm,
    footskip=12mm
]{geometry}

% 超链接
\RequirePackage[hidelinks]{hyperref}

% 页眉页脚
\RequirePackage{fancyhdr}
\pagestyle{fancy}
\fancyhf{}
\lhead{\textnormal{\rightmark}}
\fancyfoot[C]{\thepage}
\renewcommand\sectionmark[1]{
    \markright{\CTEXifname{\CTEXthesection~--~}{}#1}}