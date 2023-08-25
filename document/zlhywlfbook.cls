% 指定当前类必须使用的格式
\NeedsTeXFormat{LaTeX2e}
%  标识类
\ProvidesClass{zlhywlfbook}[2022/12/12 v1.0.0 zlhywlfbook document class]
% 加载包
\RequirePackage{kvoptions}
% 设置选项前缀
\SetupKeyvalOptions{family=ZLHYWLF, prefix=ZLHYWLF@, setkeys=\kvsetkeys}

% =========================================
% 处理配置选项
% =========================================

% 定义字符串选项
\DeclareStringOption[blue]{color}
% 设置设备类型 pad normal
\DeclareStringOption[normal]{device}
% 设置页面边缘 false true
\DeclareStringOption[false]{marginpar}

% 处理传入的参数
\ProcessKeyvalOptions*\relax
% 加载类
\LoadClass{book}

% 定义命令

% 超链接
\RequirePackage{hyperref}
\hypersetup{
    bookmarksnumbered=true, % 书签中章节编号，默认 true
    bookmarksopen=true, % 书签目录展开，默认 true
    breaklinks, % 允许链接断行，默认 false
    citecolor=winered, % 引用标记颜色，默认 green
    colorlinks, % 彩色链接，默认 true
    linkcolor=winered, % 内部链接颜色，默认 red
    linktoc=all, % 链接所有
    linktocpage, % 目录页码链接，默认值 false
    unicode, % unicode 编码，默认 false
    urlcolor=winered, % 网页与邮件链接颜色，默认 magenta
    pdfborder={0 0 0}, % 链接边框，默认值 无边框 {0 0 0}，有框 {0 0 1}
    pdfkeywords={zlhywlfBook}, % 关键字
    pdfstartview=FitH, % PDF 文件初始视图｛FitH 页宽适合窗口，FitV 页高适合窗口，FitR 适合窗口对角线，FitB 版面适合窗口｝
    plainpages=false % 页锚编号，默认 true
}

% 页面尺寸
\RequirePackage{geometry}
% 工具包
\RequirePackage{etoolbox}

% =========================================
% 页面设置
% =========================================

\ifdefstring{\ZLHYWLF@device}{pad}{
    \geometry{
        paperwidth=7.5in,paperheight=10in,
        margin=16mm,
        headheight=2.17cm,
        footskip=4mm
    }
}{
    \geometry{
        a4paper,
        top=25.4mm, bottom=25.4mm,
        left=20mm, right=20mm,
        headheight=2.17cm,
        headsep=4mm,
        footskip=12mm
    }
}

\ifdefstring{\ZLHYWLF@marginpar}{true}{
    \geometry{
        marginparwidth=5cm, marginparsep=5mm,
        left=2cm,right=7cm
    }
}{\relax}




% 临时
\newcommand{\helloworld}{
    hello~world!

    你好，世界！

    $y=x+1$

    color = \ZLHYWLF@color

    marginpar = \ZLHYWLF@marginpar

    device = \ZLHYWLF@device
}