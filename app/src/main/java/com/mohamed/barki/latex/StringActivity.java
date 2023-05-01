package com.mohamed.barki.latex;

import android.app.Activity;
import android.os.Bundle;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class StringActivity extends Activity
{
	public static String[][] strAlpha = new String[][]{
        {"℧", "Ω var", "Ω", "ω", "Ψ var", "Ψ", "ψ", "χ", "Φ var", "Φ", "ϕ", "φ", "Υ", "υ", "τ", "Σ var", "Σ", "σ", "ς", "ρ", "ϱ", "Π var", "Π", "π", "ϖ", "ο", "Ξ", "ξ", "ν", "μ", "Λ", "λ", "κ", "ϰ", "ι", "Θ", "θ", "ϑ", "η", "ζ", "Ϝ", "ϝ", "∍", "ε", "ϵ", "∇", "Δ var", "Δ", "δ", "Γ var", "Γ", "γ", "β","α"},
        {"\\mho", "\\varOmega", "\\Omega", "\\omega", "\\varPsi", "\\Psi", "\\psi", "\\chi", "\\varPhi", "\\Phi", "\\phi", "\\varphi", "\\Upsilon", "\\upsilon", "\\tau", "\\varSigma", "\\Sigma", "\\sigma", "\\varsigma", "\\rho", "\\varrho", "\\varPi", "\\Pi", "\\pi", "\\varpi", "\\omicron", "\\Xi", "\\xi", "\\nu", "\\mu", "\\Lambda", "\\lambda", "\\kappa", "\\varkappa", "\\iota", "\\Theta", "\\theta", "\\vartheta", "\\eta", "\\zeta", "\\Digamma", "\\digamma", "\\backepsilon", "\\varepsilon", "\\epsilon", "\\nabla", "\\varDelta", "\\Delta", "\\delta", "\\varGamma", "\\Gamma", "\\gamma", "\\beta", "\\alpha"}                  
    };
    public static String[][] strAlphaPro = new String[][]{
        {"¥", "≀", "℘", "℘", "√", "§", "§", "£", "⋔", "∂", "¶", "♯", "♮", "⋋", "⋌", "ℜ", "ℜ", "ℑ", "ℑ", "ȷ", "ı", "ℏ", "ℏ", "⅁", "♭", "Ⅎ", "ð", "ℓ", "ℸ", "⨿", "ℷ", "ℶ", "ℵ"},
        {"\\yen", "\\wr", "\\wp", "\\weierp", "\\surd", "\\sect", "\\S", "\\pounds", "\\pitchfork", "\\partial", "Pi", "\\sharp", "\\natural", "\\leftthreetimes", "\\rightthreetimes", "\\real", "\\Re", "\\image", "\\Im", "\\j", "\\i", "\\hslash", "\\hbar", "\\Game", "\\flat", "\\Finv", "\\eth", "\\ell", "\\daleth", "\\amalg", "\\gimel", "\\beth", "\\alef"}
    };
    public static String[][] strSymboleGeometry = new String[][]{
        {"⋆", "∢", "¬", "✠", "Ⓢ", "®", "⊝", "⊚", "⊛", "↶", "↷", "↻", "↺", "✓", "∴", "∵", "⊸", "∡", "∨", "¬", "┐", "┘", "└", "┌", "∧", "⋉", "⋈", "⋊", "⊶", "⊷", "⋏", "⋎", "≗", "∘", "◯", "⊼", "∠"},
        {"\\star", "\\sphericalangle", "\\neg", "\\maltese", "\\circledS", "\\circledR", "\\circleddash", "\\circledcirc", "\\circledast", "\\curvearrowleft", "\\curvearrowright", "\\circlearrowright", "\\circlearrowleft", "\\checkmark", "\\therefore", "\\because", "\\multimap", "\\measuredangle", "\\lor", "\\lnot", "\\urcorner", "\\lrcorner", "\\llcorner", "\\ulcorner", "\\landa", "\\ltimes", "\\Join", "\\rtimes", "\\origof", "\\imageof", "\\curlywedge", "\\curlyvee", "\\circeq", "\\circ", "\\bigcirc", "\\barwedge", "\\angle"}
    };
    public static String[][] strOperator = new String[][]{
        {"th", "tg", "tanh", "tan", "sup", "sh", "sinh", "sin", "sec", "Pr", "plim", "log", "ln", "lg", "ker", "injlim", "inf", "hom", "gcd", "exp", "dim", "det", "cos", "cosec", "cosh", "cot", "cotg", "coth", "csc", "ctg", "cth", "argmax", "argmin", "arg", "arctg", "arctan", "arcsin", "arcctg", "arccos"},
        {"\\th", "\\tg", "\\tanh", "\\tan", "\\sup", "\\sh", "\\sinh", "\\sin", "\\sec", "\\Pr", "\\plim", "\\log", "\\ln", "\\lg", "\\ker", "\\injlim", "\\inf", "\\hom", "\\gcd", "\\exp", "\\dim", "\\det", "\\cos", "\\cosec", "\\cosh", "\\cot", "\\cotg", "\\coth", "\\csc", "\\ctg", "\\cth", "\\argmax", "\\argmin", "\\arg", "\\arctg", "\\arctan", "\\arcsin", "\\arcctg", "\\arccos"}
    };
    public static String[][] strApostrof = new String[][]{
        {"`", "′", "‘", "Œ", "œ", "Æ", "æ", "Å", "å", "å", "â", "ã", "ā", "à", "ė", "ä", "á"},
        {"\\rq", "\\prime", "\\lq", "\\OE", "\\oe", "\\AE", "\\ae", "\\AA", "\\aa", "\\r{a}", "\\^{a}", "\\~{a}", "\\={a}", "\\`{a}", "\\.{e}", "\\\"{a}", "\\'{a}"}
    };
	public static String[][] strMaths = new String[][]{
        {"+", "-", "*", "/", "^", "=", "_", "~", "#", "$", "\\", "&", "%", "|"},
        {"+", "-", "*", "/", "^", "=", "_", "~", "#", "$", "\\", "&", "%", "|"}
    };
	public static String[][] strBrackets = new String[][]{
        {"\\begin{}\n \n\\end{}", "\\begin{equation*}\n \n\\end{equation*}", "\\begin{equation}\n \n\\end{equation}", "$$$$", "\\[\\]", "\\(\\)", "\\{\\}", "$$", "<>", "[]", "()", "{}"},
        {"\\begin{}\n \n\\end{}", "\\begin{equation*}\n \n\\end{equation*}", "\\begin{equation}\n \n\\end{equation}", "$$$$", "\\[\\]", "\\(\\)", "\\{\\}", "$$", "<>", "[]", "()", "{}"}
    };
	public static String[][] strFunction = new String[][]{
		{//other 0
			"36\\degree", "36^\\circ", "\\mathring{36}", "ab\\mathinner{\\text{inside}}cd", "a\\centerdot b", "\\check{oe}", "\\breve{eu}", "\\grave{eu}", "\\angl", "\\angln",
			"\\dot x", "\\ddot x", "\\H{a}", "\\hat{\theta}", "\\text{\\u{a}}", "\\text{\\v{a}}", 
			"\\cancel{5}", "\\bcancel{5}", "\\xcancel{ABC}", "\\sout{abc}", 
			"\\mathsterling", "\\minuso", "I\\kern-2.5pt R", "1\\mathord{,}234{,}567"
		},
		{//text
	        "\\textbf{AaBb123}", "\\textit{AaBb}", "\\textnormal{AB}", "\\textmd{AaBb123}", "\\textrm{AaBb123}", "\\textsf{AaBb123}", "\\texttt{AaBb123}", "\\textup{AaBb123}", 
			"\\textcolor{blue}{F=ma}", "\\textstyle\\sum_0^n", 
			"\\text{ text in math equation }",
			"\\text{\\textbardbl}", "\\text{\\textbar}", "\\text{\\textasciicircum}", "\\text{\\textbackslash}", 
			"\\text{\\textdegree}", "\\text{\\textcircled}", "\\text{\\textbraceright}", "\\text{\\textbraceleft}",
			"\\text{\\textless}", "\\text{\\textgreater}", "\\text{\\textendash}", "\\text{\\textemdash}", "\\text{\\textellipsis}", "\\text{\\textdollar}",
			"\\text{\\textunderscore}", "\\text{\\textsterling}", "\\text{\\textregistered}", "\\text{\\textquoteleft}", "\\text{\\textquoteright}", "\\text{\\textquotedblright}", "\\text{\\textquotedblleft}",
		},
		{//ensemble
			"\\cnums", "\\Complex", "\\N", "\\natnums", "\\R", "\\reals", "\\Reals", "\\Z", 
			"\\emptyset", "\\empty", "\\O", "\\o", "\\complement", "\\varnothing", "\\imath", "\\jmath", "\\ss", 
		},
		{//font
			"\\Bbb{ABC}", "{\\it AaBb}", "{\\tt AaBb123}", "\\Bbbk", "\\boxed{ab}", "\\bf AaBb12", "\\bm{AaBb}", "\\rm AaBb12", "\\pmb{\\mu}", "\\bold{AaBb123}", "\\boldsymbol{AaBb}", "\\cal AaBb123", "\\mathcal{AaBb123}", "\\frak{AaBb}", "\\mathfrak{AaBb}", "\\mathscr{ABC}", "\\mathbb{AB}", "\\mathbb{k}", "\\mathbf{AaBb123}", "\\mathit{AaBb}", "\\mathnormal{AaBb}", "\\mathrm{AaBb123}", "\\mathsf{AaBb123}", "\\mathtt{AaBb123}",
			"\\color{#0000FF} AaBb123", "\\colorbox{red}{Black on red}", "\\fcolorbox{red}{green}{Black on green with board red}",
			"\\huge huge", "\\Huge Huge", "\\large large", "\\Large Large", "\\LARGE LARGE", "\\normalsize normalsize", "\\footnotesize footnotesize", "\\scriptsize scriptsize", "\\sixptsize sixptsize", "\\small small", "\\tiny tiny", 
			"\\displaystyle displaystyle math", "\\scriptscriptstyle scriptscriptstyle math", "\\scriptstyle scriptstyle math", 
			"\\includegraphics[height=0.8em, totalheight=0.9em, width=0.9em, alt=KA logo]{https://cdn.kastatic.org/images/apple-touch-icon-57x57-precomposed.new.png}",
			"\\url{https://barkiapk.blogspot.com/}", "\\href{https://www.facebook.com/profile.php?id=3203834/}{url clickable}", "\\htmlClass{foo}{Html Class}", "\\htmlData{foo=a, bar=b}{Html Data}", "\\htmlId{bar}{Html Id}", "\\htmlStyle{color: red;}{Html Style}"
		},
		{//space
			"a\\ b", "a\\, b", "a\\: b", "a\\; b", "a\\> b", "a~b", "a\\space b", "a\\enspace b", "a\\medspace b", "a\\negmedspace b", "a\\thickspace b", "a\\thinspace b", "a\\negthickspace b", "a\\negthinspace b", "a\\nobreakspace b", "a\\quad\\quad{b}", "a\\qquad\\qquad{b}", "a\\hphantom{bc}d", "a\\mathchoice{\\,}{\\,\\,}{\\,\\,\\,}{\\,\\,\\,\\,}b", "h\\raisebox{2pt}{ighe}r",
			"w\\hskip1em i\\hskip2em d", "a\\mkern18mu b", "a\\mskip{10mu}b",
			"textA \\hspace*{2cm} textB", "textA\n \\vspace*{1cm} textB",
			"line1 \newline line2", "line1 \\\\ line2"
		},
		{//matrix 1
			"\\begin{matrix}\n a & b \\\\\n c & d \n\\end{matrix}",
			"\\begin{Bmatrix}\n a & b \\\\\n c & d \n\\end{Bmatrix}",
			"\\begin{Bmatrix*}[r]\n 0 & -1 \\\\\n -1 & 0 \n\\end{Bmatrix*}",
			"\\begin{bmatrix}\n a & b \\\\\n c & d \n\\end{bmatrix}",
	        "\\begin{bmatrix*}[r]\n 0 & -1 \\\\\n -1 & 0 \n\\end{bmatrix*}",
			"\\begin{pmatrix}\n a & b \\\\\n c & d \n\\end{pmatrix}",
			"\\begin{pmatrix*}[r]\n 0 & -1 \\\\\n -1 & 0 \n\\end{pmatrix*}",
			"\\begin{smallmatrix}\n a & b \\\\\n c & d \n\\end{smallmatrix}", 
			"\\begin{Vmatrix}\n a & b \\\\\n c & d \n\\end{Vmatrix}",
			"\\begin{Vmatrix*}[r]\n 0 & -1 \\\\\n -1 & 0 \n\\end{Vmatrix*}", 
			"\\begin{vmatrix}\n a & b \\\\\n c & d \n\\end{vmatrix}", 
			"\\begin{vmatrix*}[r]\n 0 & -1 \\\\\n -1 & 0 \n\\end{vmatrix*}", 
			"\\begin{align}\n a&=b+c \\\\\n d+e&=f \n\\end{align}",
			"\\begin{align}\n a&=b+c \\nonumber\\\\\n d+e&=f \n\\end{align}",
			"\\begin{align}\n a&=b+c \\notag\\\\\n d+e&=f \n\\end{align}",
			"\\begin{align*}\n a&=b+c \\\\\n d+e&=f \n\\end{align*}",
			"\\begin{aligned}\n a&=b+c \\\\\n d+e&=f \n\\end{aligned}",
			"\\begin{alignat}{2}\n 10&x+ &3&y = 2 \\\\\n 3&x+&13&y = 4 \n\\end{alignat}",
			"\\begin{alignat*}{2}\n 10&x+ &3&y = 2 \\\\\n 3&x+&13&y = 4 \n\\end{alignat*}",
			"\\begin{alignedat}{2}\n 10&x+ &3&y = 2 \\\\\n 3&x+&13&y = 4 \n\\end{alignedat}",
			"\\begin{array}{ccl}\n a & b & c \\\\\n d & e & f \n\\end{array}",
			"\\begin{darray}{cc}\n a & b \\\\\n c & d \n\\end{darray}",
			"\\begin{array}{c|c}\n a & b \\\\\n c & d \n\\end{array}",
			"\\begin{array}{c|c}\n a & b \\\\\n\\hline c & d \n\\end{array}",
			"\\begin{array}{c|c}\n a & b \\\\\n\\hdashline c & d \n\\end{array}",
			"\\begin{cases}\n a &\\text{if } b \\\\\n c &\\text{if } d \n\\end{cases}",
			"\\begin{rcases}\n a &\\text{if } b \\\\\n c &\\text{if } d \n\\end{rcases}",
			"\\begin{CD}\n A @>a>> B \\\\\n @VbVV @AAcA \\\\\n C @= D \n\\end{CD}",
			"\\begin{dcases}\n a &\\text{if } b \\\\\n c &\\text{if } d \n\\end{dcases}",
			"\\begin{drcases}\n a &\\text{if } b \\\\\n c &\\text{if } d \n\\end{drcases}",
			"\\begin{gather}\n a=b \\\\\n e=b+c \n\\end{gather}",
			"\\begin{gathered}\n a=b \\\\\n e=b+c \n\\end{gathered}"
		},
		{//equation
			"\\begin{equation}\n  \\begin{split}\n    a &=b+c\\\\\n     &=e+f\n  \\end{split}\n\\end{equation}",
			"\\tag{3.1c} a^2+b^2=c^2", 
			"\\tag*{3.1c} a^2+b^2=c^2", 
		},
		{//brackets []
			"\\lbrack \\rbrack", "\\llbrack \\rrbrack", "\\llbracket \\rrbracket", "\\big[\\big]", "\\Big[\\Big]", "\\bigg[\\bigg]", "\\Bigg[\\Bigg]",
			"\\lceil", "\\rceil", "\\lfloor", "\\rfloor",
			"\\vert", "\\Vert", "\\lvert", "\\rvert", "\\lVert", "\\rVert", "\\bigm\\vert", "\\Bigm\\vert",  "\\biggm\\vert", "\\Biggm\\vert",
			"\\left[\\dfrac a b\\right.", "\\left.\\dfrac a b\\right]"
		},
		{//braces {}
			"\\lbrace \rbrace", "\\llbrace \rrbrace", "\\lBrace \rBrace", "\\big\\{\\big\\}", "\\Big\\{\\Big\\}", "\\bigg\\{\\bigg\\}", "\\Bigg\\{\\Bigg\\}",
			"\\left\\{\\dfrac a b\\right.", "\\left.\\dfrac a b\\right\\}"
		},
		{//parentheses ()
			"\\big(\\big)", "\\Big(\\Big)", "\\bigg(\\bigg)", "\\Bigg(\\Bigg)", "\\bigl", "\\Bigl",  "\\biggl", "\\Biggl", "\\bigr", "\\Bigr",  "\\biggr", "\\Biggr",
			"\\lgroup", "\\rgroup", "\\lparen", "\\rparen",
			"\\left(\\dfrac a b\\right.", "\\left.\\dfrac a b\\right)"
		},
		{//integral 
            "\\int", "\\intop", "\\smallint", "\\int_{a}^{b}", "\\int\\limits_{a}^{b}", "\\iint", "\\iiint", "\\oint", "\\oiint", "\\oiiint", "\\lmoustache", "\\rmoustache"
		},
		{//sum 
			"\\sum", "\\sum_{a}^{b}", "\\sum\\limits_{a}^{b}", "\\sum_{\\mathclap{1\\le i\\le n}} x_{i}", "\\sum_{\\substack{0 \\le i \\le m \\\\ 0 \\le j \\le n }}"
		},
		{//prod
			"\\prod", "\\prod_{a}^{b}", "\\prod\\limits_{a}^{b}", "\\prod_{\\mathclap{1\\le i\\le n}} x_{i}", "\\coprod"
		},
		{//frac 
			"\\frac{1}{x+1}", "\\dfrac{a-1}{b-1}", "\\tfrac ab", "\\verb!\\frac a b!", "{a \\above{2pt} b}", "{a \\atop b}", "{a+1 \\over b+2}+c", "a+\\left(\\vcenter{\\hbox{\\frac{\\frac a b}c}}\\right)", "a+\\left(\\vcenter{\\frac{\\frac a b}c}\\right)", 
			"{n\\brace k}", "{n\\brack k}", "\\binom n k", "\\tbinom n k", "\\dbinom n p", "{n+1 \\choose k+2}", "\\cfrac{2}{1+\\cfrac{2}{1+\\cfrac{2}{1}}}", "\\genfrac ( ] {2pt}{0}a{a+1}", "\\pu{123 kJ//mol}"
		},
		{//lim
			"\\lim", "\\lim_{x}", "\\lim\\nolimits_{x}", "\\lim\\limits_{x\\to 0}", "\\liminf", "\\varliminf", "\\limsup", "\\varlimsup", "\\min", "\\min\\limits_{x\\in A}", "\\max", "\\max\\limits_{x\\in A}", "\\projlim", "\\varprojlim", 
		},
		{//underline
			"\\underbar{X}", "\\tilde{AB}", "\\underset{!}{=}", "\\underrightarrow{AB}", "\\underlinesegment{AB}", "\\underline{\text{a long argument}}", "\\underrightarrow{AB}", "\\underleftrightarrow{AB}", "\\underleftarrow{AB}", "\\undergroup{AB}", "\\underbrace{x+\\cdots x}_{n\\text{ times}}", "\\xlongequal{abc}", 
		},
		{//overline
			"\\widehat{AB}", "\\widecheck{AB}", "\\overline{\\vphantom{M}a}", "\\bar{X}", "\\utilde{AB}", "\\widetilde{AB}", "\\stackrel{!}{=}", "\\overset{!}{=}", "\\overrightharpoon{ac}", "\\overrightarrow{AB}", "\\Overrightarrow{AB}", "\\overlinesegment{AB}", "\\overline{\text{a long argument}}", "\\overleftrightarrow{AB}", "\\overleftharpoon{AB}", "\\overleftarrow{AB}", "\\overgroup{AB}", "\\overbrace{x\\cdots x}^{n\\text{ times}}", "x\\rule[6pt]{2ex}{1ex}x"
		},
		{//function
			"\\"
		},
		{//cup cap 
			"\\cap", "\\cap_{a}^{b}", "\\bigcap", "\\bigcap_{a}", "\\bigcap^{b}", "\\bigcap\\limits_{a}^{b}",
			"\\cup", "\\cup_{a}^{b}", "\\bigcup", "\\bigcup_{a}", "\\bigcup^{b}", "\\bigcup\\limits_{a}^{b}",
			"\\sqcup", "\\bigsqcup", "\\bigsqcup_{a}", "\\bigsqcup^{b}", "\\bigsqcup_{a}^{b}", "\\bigsqcup\\limits_{a}^{b}",
			"\\biguplus", "\\bigvee", "\\bigwedge", "\\doublebarwedge", "\\Cap", "\\Cup", "\\doublecap", "\\doublecup",
			"\\sqcap", "\\sqcup", "\\uplus", 
		},
		{//arrow⇓
			"\\Updownarrow", "\\Uparrow", "\\uArr", "\\Uarr", "\\Rrightarrow", "\\Rightarrow", "\\Rarr", "\\rArr", "\\nRightarrow", "\\nLeftrightarrow", "\\nLeftarrow", "\\lrArr", "\\Lrarr", "\\Longrightarrow", "\\Longleftrightarrow", "\\Longleftarrow", "\\Lleftarrow", "\\Leftrightarrow", "\\Leftarrow", "\\Larr", "\\lArr", "P \\impliedby Q", "Q\\implies P", "A\\iff B", "\\hArr", "\\Harr", "\\Darr", "\\dArr", "\\Downarrow",
			"\\xRightarrow{abc}", "\\xLeftrightarrow{abc}", "\\xLeftarrow{abc}"
		},
		{//dasharrow
			"\\upuparrows", "\\upharpoonleft", "\\upharpoonright", "\\updownarrow", "\\uparrow", "\\uarr", "\\twoheadrightarrow", "\\twoheadleftarrow", "\\to", "\\swarrow", "\\searrow", "\\Rsh", "\\rightsquigarrow", "\\rightrightarrows", "\\rightleftharpoons", "\\rightleftarrows", "\\rightharpoonup", "\\rightharpoondown", "\\rightarrowtail", "\\rightarrow", "\\restriction", "\\rarr", "\\nwarrow", "\\nrightarrow", "\\nleftrightarrow", "\\nleftarrow", "\\nearrow", "\\mapsto", "\\Lsh", "\\lrarr", "\\looparrowright", "\\looparrowleft", "\\longrightarrow", "\\longmapsto", "\\longleftrightarrow", "\\longleftarrow", "\\leftrightharpoons", "\\leftrightarrows", "\\leftrightarrow", "\\leftleftarrows", "\\leftharpoonup", "\\leftharpoondown", "\\leftrightsquigarrow", "\\leftarrow", "\\leadsto", "\\larr", "\\hookleftarrow", "\\hookrightarrow", "\\harr", "\\gets", "\\downharpoonright", "\\downharpoonleft", "\\downdownarrows", "\\downarrow", "\\darr", "\\dashleftarrow", "\\dashrightarrow",
			"\\xtwoheadrightarrow{abc}", "\\xtwoheadleftarrow{abc}", "\\xtofrom{abc}", "\\xrightleftharpoons{abc}", "\\xrightharpoonup{abc}", "\\xrightharpoondown{abc}", "\\xrightarrow{abc}", "\\xmapsto{abc}", "\\xleftrightharpoons{abc}", "\\xleftrightarrow{abc}", "\\xleftharpoonup{abc}", "\\xleftharpoondown{abc}", "\\xleftarrow{abc}", "\\xhookrightarrow{abc}", "\\xhookleftarrow{abc}"
		},
		{//equal
			"\\approx", "\\approxcolon", "\\approxcoloncolon", "\\approxeq", "\\asymp",
			"\\backsim", "\\backsimeq", "\\Bumpeq", "\\bumpeq", 
			"\\ratio", "\\colon", "\\dblcolon", "\\Colonapprox", "\\colonapprox", "\\coloncolon", "\\coloncolonapprox", "\\coloncolonequals", "\\coloncolonminus", "\\coloncolonsim", "\\Coloneq", "\\coloneq", "\\colonequals", "\\Coloneqq", "\\coloneqq", "\\colonminus", "\\Colonsim", "\\colonsim", "\\cong",
			"\\Doteq", "\\doteq", "\\doteqdot", "\\eqcirc", "\\Eqcolon", "\\eqcolon", "\\Eqqcolon", "\\eqqcolon", "\\eqsim", "\\equalscolon", "\\equalscoloncolon", "\\equiv", "\\fallingdotseq", "\\neq", "{=}\\llap{/\\,}", "{=}\\mathllap{/\\,}", "\\mathrlap{\\,/}{=}", "\\rlap{\\,/}{=}", 
			"\\ncong", "\\sim", "\\thicksim", "\\thickapprox", "\\simcolon", "\\simcoloncolon", "\\simeq", "\\nsim", "\\risingdotseq", 
		},
		{//operation
			"\\mp", "\\pm", "\\plusmn", "\\minuscolon", "\\minuscoloncolon", "\\div", "\\times", "\\divideontimes", "\\dotplus", "A\\mathpunct{-}B",
			"a^{b}", "\\left(x^{\\smash{2}}\\right)", "a_{b}", "\\Gamma^{\\phantom{i}j}_{i\\phantom{j}k}", "\\mathop{\\star}_a^b",
			"\\sqrt[n]{x}", "\\sqrt{x}", 
			"a \\bmod b", "x\\pmod a", "x \\pod a", "3\\equiv 5 \\mod 2", "a\\mathbin{!}b", "a \\mathrel{\\#} b", "\\backslash", "\\ast", "=", "\not =", "\\mathrel{\\vcentcolon =}", 
			"\\_",
			"\\ominus", "\\oplus", "\\oslash", "\\otimes",
			"\\parallel", "\\nparallel", "\\shortparallel", "\\nshortparallel", "|", "\\mind", "\\nmid", "\\shortmind", "\\nshortmind", "\\$", "\\&", "\\%", "\\#"
		},
		{//relation geq leq ...
			"\\nless", "\\nleqslant", "\\nleq", "\\nleqq", "\\ngtr", "\\ngeqslant", "\\ngeqq", "\\ngeq", "\\lvertneqq", "\\lnsim", "\\lnot", "\\lneqq", "\\lneq", "\\lesssim", "\\lessgtr", "\\lesseqqgtr", "\\lesseqgtr", "\\lessdot", "\\lessapprox", "\\leqslant", "\\leqq", "\\gvertneqq", "\\gtrsim", "\\gtrless", "\\gtreqqless", "\\gtreqless", "\\gtrapprox", "\\gt", "\\lt", "\\gnsim", "\\gneqq", "\\gneq", "\\gnapprox", "\\ll", "\\lll", "\\llless", "\\gg", "\\ggg", "\\gggtr", "\\geqslant", "\\geq", "\\geqq", "\\le", "\\ge", "\\eqslantless", "\\eqslantgtr",
			"\\prec", "\\precapprox", "\\preccurlyeq", "\\preceq", "\\precnapprox", "\\precneqq", "\\precnsim", "\\precsim", 
			"\\succsim", "\\succnsim", "\\succneqq", "\\succnapprox", "\\succeq", "\\succcurlyeq", "\\succapprox", "\\succ", 
			"\\curlyeqsucc", "\\curlyeqprec", "\\nprec", "\\npreceq", "\\nsucc", "\\nsucceq",
			"a + \\mathopen\\lt b) + c", "a + (b\\mathclose\\gt + c", "\\ket{\\psi}", "\\Ket{\\psi}", "\\bra{\\psi}", "\\Bra{\\psi}", "\\lang A\\rangle", "\\langle A\\rang"
		},
		{//relation geometrique
			"\\wedge", "\\Vvdash", "\\veebar", "\\vee", "\\vdash", "\\vDash", "\\Vdash", "\\varsubsetneqq", "\\varsubsetneq", "\\varsupsetneqq", "\\varsupsetneq", "\\varpropto", "\\top", "\\supsetneqq", "\\supsetneq", "\\supseteqq", "\\supseteq", "\\supset", "\\Supset", "\\supe", "\\subsetneqq", "\\subsetneq", "\\subseteqq", "\\subseteq", "\\subset", "\\Subset", "\\sube", "\\sub", "\\sqsubset", "\\sqsubseteq", "\\sqsupset", "\\sqsupseteq", "\\propto", "\\phase{-78^\\circ}", "\\perp", "\\nvdash", "\\nvDash", "\\nVdash", "\\nVDash", "\\nsupseteq", "\\nsupseteqq", "\\nsubseteq", "\\nsubseteqq", "\\in", "\\notin", "\\owns", "\\ni", "\\notni", "\\models", "\\isin", "\\intercal", "\\infty", "\\infin", "\\forall", "\\exists", "\\exist", "\\nexists", "\\bot", "\\dashv", "\\{X\\mid Y\\}", "P\\left(A\\middle\\vert B\\right)"
		},
		{//dot
			"\\vdots", "\\sdot", "\\mathellipsis", "\\ldotp", "\\ldots", "\\cdot", "\\cdotp", "\\cdots", "\\ddots", "x_1 + \\dots + x_n", "x_1 + \\dots + x_n", "x,\\dotsc,y", "\\int_{A_1}\\dotsi\\int_{A_n}", "x_1 x_2 \\dotsm x_n", "\\dotso"
		},
		{//phisique
			"\\ce{C6H5-CHO}"
		},
		{//circl 
			"\\odot", "\\bigodot", "\\bigoplus"
		},
		{//form
			"\\smallsetminus", "\\smallsmile", "\\smile", "\\smallfrown", "\\frown", "\\copyright", "\\diagdown", "\\diagup"
		},
		{//form black 
			"\\spades", "\\spadesuit", "\\clubs", "\\clubsuit", "\\bigstar", "\\blacklozenge", "\\blacksquare", "\\blacktriangle", "\\blacktriangledown", "\\blacktriangleleft", "\\blacktriangleright", "\\bull", "\\bullet"
		},
		{//form empty 
			"\\vartriangleright", "\\vartriangleleft", "\\vartriang", "\\unlhd", "\\unrhd", "\\trianglerighteq", "\\triangleright", "\\triangleq", "\\trianglelefteq", "\\triangleleft", "\\triangledown", "\\triangle", "\\square", "\\rhd", "\\ntriangleright", "\\ntrianglerighteq", "\\ntriangleleft", "\\ntrianglelefteq", "\\lozenge", "\\lhd", "\\heartsuit", "\\hearts", "\\diamondsuit", "\\diamonds", "\\diamond", "\\Diamond", "\\bigtriangledown", "\\bigtriangleup", "\\bowtie", "\\Box", "\\boxdot", "\\boxminus", "\\boxplus", "\\boxtimes"
		},
		/*{//smile
			"\\char\"263a"
		}
		/*
		 
		 
		
		*/
		
	};
	public static String[] command = new String[]{
		"\\def\\arraystretch{1.5}", "\\edef", "\\xdef", "\\def", "\\let", "\\relax", "\\long", "\\expandafter", "\\futurelet", "\\nobreak", 
		"\\gdef\fun#1{\n %function defined by #1\n}% fun(#1)",
		"\\newcommand\\newcmnd[5]{\n %new cmnd defined by #1,...,#5\n}% newcmnd(#1, #2, ..., #5)",
		"\\def\\cmnd{\n %something\n}\n%something do with cmnd\n\\renewcommand\\cmnd{\n %something\n}",
		"\\providecommand\\providecmnd{\n %put some chose here \n}",
		"\\operatorname{operator} x %define new operator like cos sin ...",
		"\\operatorname*{opertor}\\limits_x f %define new opertor like lim min max ... with fix limits",
		"\\operatornamewithlimits{asin}\\limits_x f define new opertor like lim min max ...",
		"\\global\\def\\add#1#2{#1+#2} %\\add 2 3 result 2+3",
		"\\hbox{$equation$}"
	};
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	}
}
