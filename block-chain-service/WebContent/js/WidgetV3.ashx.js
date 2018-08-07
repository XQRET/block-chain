/* Copyright 2010 Microsoft Corporation */
window['_mstConfig'] = {
	appId: _mstConfig["appId"],
	rootURL: 'https\x3a\x2f\x2fwww.microsofttranslator.com\x2f',
    baseURL: 'https\x3a\x2f\x2fwww.microsofttranslator.com\x2fajax\x2fv3\x2fWidgetV3.ashx\x3fsiteData\x3dueOIGRSKkd965FeEGM5JtQ\x2a\x2a',
    serviceURL: 'https\x3a\x2f\x2fapi.microsofttranslator.com\x2fv2\x2fajax.svc',
    imagePath: 'https\x3a\x2f\x2fwww.microsofttranslator.com\x2fstatic\x2f25550255\x2fimg\x2f',
    debug: false,
    locale: 'zh-chs',
    country: '',
    category: '',
    ref: 'WidgetV3CTF',
    service: 'WC3',
    maxChars: 1000000000,
    noAuto: ["facebook.", "youtube."],
    escapeNonAscii: false,
    requestGroup: '',
    preTrans: false,
    OnErrorHandler: '',
    WidgetSiteDomain: ''
};﻿window._mstConfig['SignIn'] = '<a href="https://login.live.com/login.srf?wa=wsignin1.0&amp;rpsnv=13&amp;ct=1533091882&amp;rver=6.7.6636.0&amp;wp=LBI&amp;wreply=https:%2F%2Fwww.microsofttranslator.com%2Fajax%2Fv2%2Fauth.aspx%3Fpru%3Dhttp%253a%252f%252fwww.microsofttranslator.com%252fajax%252fv3%252fWidgetV3.ashx&amp;lc=2052&amp;id=268160">登录</a>';
if (!this.Microsoft) this.Microsoft = {};
if (!this.Microsoft.Translator) this.Microsoft.Translator = {};
if (Microsoft.Translator.Reset) Microsoft.Translator.Reset();
Microsoft.Translator = new
function() {
    var ub = "WidgetFloaterPanels",
    W = "hidden",
    J = "none",
    F = "8px",
    P = "pointer",
    cb = "2147483647",
    bb = "absolute",
    ib = "none",
    ab = "direction",
    hb = "&onerror=",
    M = "lang",
    V = "font",
    x = "img",
    E = "center",
    U = "false",
    D = "left",
    R = "right",
    w = 100,
    v = 255,
    B = "div",
    gb = "position",
    n = 400,
    r = "px",
    xb = "localizedLangs",
    i = "Original",
    ob = "ta",
    tb = "sv",
    o = "es",
    nb = "sr-latn",
    mb = "sr-cyrl",
    Z = "ru",
    Q = "pt",
    I = "no",
    sb = "ms",
    lb = "sw",
    rb = "it",
    Y = "de",
    g = "fr",
    X = "nl",
    qb = "zh-cht",
    kb = "zh-chs",
    fb = "ca",
    m = "ar",
    t = "rtl",
    d = "ltr",
    u = "&",
    T = "TRNS_ERROR_MSG",
    L = "none",
    O = "iframe",
    S = "string",
    f = 16,
    l = true,
    H = "number",
    K = "function",
    A = "undefined",
    N = "head",
    eb = "text/javascript",
    e = -1,
    s = "/",
    z = "_mstConfig",
    a = "en",
    k = false,
    jb = "/static/img/",
    b = "",
    j = null,
    q = this;
    q.AddTranslation = function(i, b, a, j, m, h, c, e, k, l, d, f, g) {
        return new y("AddTranslation", {
            appId: i,
            originalText: b,
            translatedText: a,
            from: j,
            to: m,
            rating: h,
            contentType: c,
            category: e,
            user: k,
            uri: l
        },
        d, f, g)
    };
    q.BreakSentences = function(e, f, b, a, c, d) {
        return new y("BreakSentences", {
            appId: e,
            text: f,
            language: b
        },
        a, c, d)
    };
    q.Detect = function(d, e, a, b, c) {
        return new y("Detect", {
            appId: d,
            text: e
        },
        a, b, c)
    };
    q.DetectArray = function(d, e, a, b, c) {
        return new y("DetectArray", {
            appId: d,
            texts: e
        },
        a, b, c)
    };
    q.GetAppIdToken = function(g, c, a, b, d, e, f) {
        return new y("GetAppIdToken", {
            appId: g,
            minRatingRead: c,
            maxRatingWrite: a,
            expireSeconds: b
        },
        d, e, f)
    };
    q.GetLanguageNames = function(f, e, a, b, c, d) {
        return new y("GetLanguageNames", {
            appId: f,
            locale: e,
            languageCodes: a
        },
        b, c, d)
    };
    q.GetLanguagesForSpeak = function(d, a, b, c) {
        return new y("GetLanguagesForSpeak", {
            appId: d
        },
        a, b, c)
    };
    q.GetLanguagesForTranslate = function(d, a, b, c) {
        return new y("GetLanguagesForTranslate", {
            appId: d
        },
        a, b, c)
    };
    q.GetTranslations = function(f, h, g, i, a, d, b, c, e) {
        return new y("GetTranslations", {
            appId: f,
            text: h,
            from: g,
            to: i,
            maxTranslations: a,
            options: d
        },
        b, c, e)
    };
    q.Translate = function(f, h, g, i, a, c, b, d, e) {
        return new y("Translate", {
            appId: f,
            text: h,
            from: g,
            to: i,
            contentType: a,
            category: c
        },
        b, d, e)
    };
    q.AddTranslationArray = function(f, a, g, h, d, b, c, e) {
        return new y("AddTranslationArray", {
            appId: f,
            translations: a,
            from: g,
            to: h,
            options: d
        },
        b, c, e)
    };
    q.GetTranslationsArray = function(f, g, h, i, a, d, b, c, e) {
        return new y("GetTranslationsArray", {
            appId: f,
            texts: g,
            from: h,
            to: i,
            maxTranslations: a,
            options: d
        },
        b, c, e)
    };
    q.Speak = function(g, h, b, f, d, a, c, e) {
        return new y("Speak", {
            appId: g,
            text: h,
            language: b,
            format: f,
            options: d
        },
        a, c, e)
    };
    q.TranslateArray = function(e, f, g, h, c, a, b, d) {
        return new y("TranslateArray", {
            appId: e,
            texts: f,
            from: g,
            to: h,
            options: c
        },
        a, b, d)
    };
    q.TranslateArray2 = function(e, f, g, h, c, a, b, d) {
        return new y("TranslateArray2", {
            appId: e,
            texts: f,
            from: g,
            to: h,
            options: c
        },
        a, b, d)
    };
    var c = {
        serviceClient: j,
        appId: b,
        lpURL: "https://www.bing.com/translator",
        rootURL: "https://www.microsofttranslator.com/",
        baseURL: "https://www.microsofttranslator.com/Ajax/V2/Toolkit.ashx",
        serviceURL: "https://api.microsofttranslator.com/V2/Ajax.svc",
        imagePath: jb,
        debug: k,
        locale: a,
        country: b,
        category: b,
        ref: b,
        service: b,
        maxChars: 1e9,
        noAuto: [],
        escapeNonAscii: k,
        requestGroup: b,
        preTrans: k
    };
    c.serviceClient = q;
    if (window[z]) {
        for (var zb in c) if (!window[z][zb]) window[z][zb] = c[zb];
        c = window[z]
    } else window[z] = c;
    var pb = c.serviceClient.LoadScript = new
    function() {
        function a(f, k) {
            var c = this,
            a = f.toString().match(/^([^:]*:\/\/[^\/]*)(\/[^\?\#]*)([\?][^#]*)*/);
            if (a) {
                c.auth = a[1] || b;
                c.path = a[2] || b;
                c.query = a[3] || b
            } else {
                a = k.toString().match(/^([^:]*:\/\/[^\/]*)(\/[^\?\#]*)([\?][^#]*)*/);
                var h = a[1] || b,
                i = a[2] || b,
                d = f.substr(0, 1) == s ? [] : i.split(s);
                a = f.match(/^([^?]*)([\?][^#]*)*$/);
                var e = (a[1] || b).split(s),
                j = a[2] || b;
                if (d.length > 0 && e.length > 0 && e[0] != ".") d.pop();
                while (e.length > 0) {
                    var g = e.shift();
                    switch (g) {
                    case ".":
                        break;
                    case "..":
                        if (d.length) d.pop();
                        break;
                    default:
                        d.push(g)
                    }
                }
                c.auth = h;
                c.path = d.join(s);
                c.query = j
            }
            c.toString = function() {
                return this.auth + this.path + this.query
            };
            return c
        }
        return function(f, i, b) {
            f = (new a(f, i ? i: new a(c.baseURL))).toString();
            b = b ? b: document;
            var g = encodeURIComponent(f).replace(/%\w\w/g, " ").length;
            if (navigator.userAgent.indexOf("MSIE") > e && g > 2048 || g > 8192) return j;
            var d = b.createElement("script");
            d.type = eb;
            d.charset = "utf-8";
            d.src = f;
            var h = b.getElementsByTagName(N)[0];
            if (h) h.appendChild(d);
            else b.documentElement.insertBefore(d, b.documentElement.firstChild);
            return d
        }
    },
    Fb = new
    function() {
        var b = {
            1 : "Array",
            2 : "Boolean",
            3 : "Date",
            4 : "Function",
            5 : "Number",
            6 : "Object",
            7 : "RegExp",
            8 : "String"
        },
        c = {
            1 : "element",
            2 : "attribute",
            3 : "text",
            4 : "cdata",
            5 : "entityReference",
            6 : "entity",
            7 : "instruction",
            8 : "comment",
            9 : "document",
            10 : "documentType",
            11 : "documentFragment",
            12 : "notation"
        },
        a = {};
        for (var d in b) a[window[b[d]]] = b[d].toLowerCase();
        return function(b) {
            if (b === undefined) return A;
            else if (b === j) return "null";
            else if (typeof b.constructor === K && a[b.constructor]) return a[b.constructor];
            else if (typeof b.nodeType === H && c[b.nodeType]) return c[b.nodeType];
            return typeof b
        }
    },
    wb = new
    function() {
        var a = j;
        if (navigator.userAgent.toLowerCase().indexOf("msie 6.") > e || navigator.userAgent.toLowerCase().indexOf("webkit") > e && (document.charset || document.characterSet || b).toLowerCase().indexOf("utf") == e) c.escapeNonAscii = l;
        var d = "\\u0000",
        q = '"#%&+:;=?@\\',
        m = ["\\x00-\\x1F", "\\x7F-\\xA0"],
        k = ["\\u02B0-\\u038F", "\\u2000-\\u20FF", "\\u3000-\\u303F"],
        i = {
            '"': '\\"',
            "\\": "\\\\"
        },
        g;
        function s() {
            g = new RegExp("[\\s" + q.replace(/./g,
            function(b) {
                var a = b.charCodeAt(0).toString(f);
                return d.substr(0, d.length - a.length) + a
            }) + m.join(b) + (c.escapeNonAscii ? "\\x7B-\\uFFFF": k.join(b)) + "]", "g");
            g.compile(g.source, "g")
        }
        function r(a) {
            if (i[a]) return i[a];
            if (a.match(/[\s\xA0]/)) return "+";
            var b = a.charCodeAt(0),
            e = b.toString(f),
            g = encodeURIComponent(a),
            h = d.substr(0, d.length - e.length) + e;
            if (g.length < h.length && b > 34 || c.escapeNonAscii && b > 122) return g;
            else return h
        }
        function h(a) {
            return a.toString().replace(g, r)
        }
        function o(a) {
            return '"' + h(a) + '"'
        }
        function p(e) {
            var c = [];
            for (var b = 0; b < e.length; ++b) {
                var d = wb(e[b]);
                if (d !== a) c.push(d)
            }
            return "[" + c.join(",") + "]"
        }
        function n(d) {
            var c = [];
            for (var b in d) {
                var e = wb(d[b]);
                if (e !== a) c.push('"' + b + '":' + e)
            }
            return "{" + c.join(",") + "}"
        }
        return function(b) {
            s();
            switch (Fb(b)) {
            case A:
                return a;
            case "null":
                return a;
            case "boolean":
                return h(b.toString());
            case H:
                return h(b.toString());
            case S:
                return o(b);
            case "array":
                return p(b);
            default:
                return n(b)
            }
        }
    },
    y = new
    function() {
        var h, g = 0,
        d = window,
        f = (document.charset || document.characterSet || b).toLowerCase();
        if (f.indexOf("utf") == e && f.indexOf("unicode") == e) try {
            c.escapeNonAscii = l;
            var a = document.createElement(O);
            a.id = "MstReqFrm";
            a.style.display = L;
            a.translate = k;
            document.documentElement.insertBefore(a, document.documentElement.firstChild);
            a.contentWindow.document.open("text/html", "replace");
            a.contentWindow.document.write('<html><head><meta charset="utf-8"><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head><body></body></html>');
            a.contentWindow.document.close();
            d = a.contentWindow
        } catch(h) {
            if (c.debug);
        }
        return function(x, n, i, q, r) {
            var h = K,
            e = ++g,
            p, f, m = k,
            v = k,
            t = b,
            A = d["_mstc" + e] = function(a) {
                setTimeout(function() {
                    if (v) {
                        o(t);
                        return
                    }
                    if (m) return;
                    setTimeout(w, 0);
                    if (i && typeof i === h) i(a)
                },
                0)
            },
            o = d["_mste" + e] = function(a) {
                setTimeout(function() {
                    var c = z;
                    if (m) return;
                    setTimeout(w, 0);
                    if (q && typeof q === h) q(a);
                    var b = window[c].OnErrorHandler,
                    d = window[c].WidgetSiteDomain;
                    if (b) if (window == window.top) {
                        if (window[b] && typeof window[b] == h) window[b](a)
                    } else if (window.parent) window.parent.postMessage(T + a.toString(), d)
                },
                0)
            };
            function w() {
                try {
                    delete d["_mstc" + e]
                } catch(a) {}
                try {
                    delete d["_mste" + e]
                } catch(a) {}
                try {
                    if (f) f.parentNode.removeChild(f)
                } catch(a) {}
                try {
                    if (p) clearTimeout(p)
                } catch(a) {}
                m = l
            }
            this.abort = function(a) {
                v = l;
                t = "The request has been aborted" + (a ? ": " + a: b)
            };
            var a = [];
            for (var s in n) if (n[s] != j) a.push(s + "=" + wb(n[s]));
            a.push("oncomplete=_mstc" + e);
            a.push("onerror=_mste" + e);
            a.push("loc=" + encodeURIComponent(c.locale));
            a.push("ctr=" + encodeURIComponent(c.country));
            if (c.ref) a.push("ref=" + encodeURIComponent(c.ref));
            a.push("rgp=" + encodeURIComponent(c.requestGroup));
            var y = "./" + x + "?" + a.join(u);
            f = pb(y, c.serviceURL, d.document);
            if (f) {
                if (typeof r === H && r > 0) p = setTimeout(function() {
                    o("The request has timed out")
                },
                r)
            } else {
                if (c.debug);
                o("The script could not be loaded")
            }
            return this
        }
    },
    Db = {
        af: "Afrikaans",
        ar: "العربية",
        bn: "বাংলা",
        "bs-Latn": "Bosanski (latinica)",
        bg: "Български",
        ca: "Català",
        "zh-CHS": "简体中文",
        "zh-CHT": "繁體中文",
        yue: "Cantonese (Traditional)",
        hr: "Hrvatski",
        cs: "Čeština",
        da: "Dansk",
        nl: "Nederlands",
        en: "English",
        et: "Eesti",
        fj: "Fijian",
        fil: "Filipino",
        fi: "Suomi",
        fr: "Français",
        de: "Deutsch",
        el: "Ελληνικά",
        ht: "Haitian Creole",
        he: "עברית",
        hi: "हिंदी",
        mww: "Hmong Daw",
        hu: "Magyar",
        is: "Íslenska",
        id: "Indonesia",
        it: "Italiano",
        ja: "日本語",
        sw: "Kiswahili",
        tlh: "Klingon",
        ko: "한국어",
        lv: "Latviešu",
        lt: "Lietuvių",
        mg: "Malagasy",
        ms: "Melayu",
        mt: "Il-Malti",
        yua: "Yucatec Maya",
        no: "Norsk",
        otq: "Querétaro Otomi",
        fa: "Persian",
        pl: "Polski",
        pt: "Português",
        ro: "Română",
        ru: "Русский",
        sm: "Samoan",
        "sr-Cyrl": "Srpski (ćirilica)",
        "sr-Latn": "Srpski (latinica)",
        sk: "Slovenčina",
        sl: "Slovenščina",
        es: "Español",
        sv: "Svenska",
        ty: "Tahitian",
        ta: "தமிழ்",
        th: "ไทย",
        to: "Lea fakatonga",
        tr: "Türkçe",
        uk: "Українська",
        ur: "اردو",
        vi: "Tiếng Việt",
        cy: "Welsh"
    },
    vb = {
        af: d,
        ar: t,
        bn: d,
        "bs-latn": d,
        bg: d,
        ca: d,
        "zh-chs": d,
        "zh-cht": d,
        yue: d,
        hr: d,
        cs: d,
        da: d,
        nl: d,
        en: d,
        et: d,
        fj: d,
        fil: d,
        fi: d,
        fr: d,
        de: d,
        el: d,
        ht: d,
        he: t,
        hi: d,
        mww: d,
        hu: d,
        is: d,
        id: d,
        it: d,
        ja: d,
        sw: d,
        tlh: d,
        "tlh-qaak": d,
        ko: d,
        lv: d,
        lt: d,
        mg: d,
        ms: d,
        mt: d,
        yua: d,
        no: d,
        otq: d,
        fa: t,
        pl: d,
        pt: d,
        ro: d,
        ru: d,
        sm: d,
        "sr-cyrl": d,
        "sr-latn": d,
        sk: d,
        sl: d,
        es: d,
        sv: d,
        ty: d,
        ta: d,
        th: d,
        to: d,
        tr: d,
        uk: d,
        ur: t,
        vi: d,
        cy: d
    },
    db = {
        "af-na": "af",
        af: "af",
        "af-za": "af",
        "ar-001": m,
        ar: m,
        "ar-ae": m,
        "ar-bh": m,
        "ar-dj": m,
        "ar-dz": m,
        "ar-eg": m,
        "ar-er": m,
        "ar-il": m,
        "ar-iq": m,
        "ar-jo": m,
        "ar-km": m,
        "ar-kw": m,
        "ar-lb": m,
        "ar-ly": m,
        "ar-ma": m,
        "ar-mr": m,
        "ar-om": m,
        "ar-ps": m,
        "ar-qa": m,
        "ar-sa": m,
        "ar-sd": m,
        "ar-so": m,
        "ar-ss": m,
        "ar-sy": m,
        "ar-td": m,
        "ar-tn": m,
        "ar-ye": m,
        "bn-bd": "bn",
        bn: "bn",
        "bn-in": "bn",
        "bs-latn-ba": "bs-latn",
        "bs-latn": "bs-latn",
        "bg-bg": "bg",
        bg: "bg",
        "ca-ad": fb,
        ca: fb,
        "ca-es": fb,
        "ca-es-valencia": fb,
        "ca-fr": fb,
        "ca-it": fb,
        "zh-cn": kb,
        "zh-chs": kb,
        "zh-hans-hk": kb,
        "zh-hans-mo": kb,
        "zh-sg": kb,
        "zh-hk": qb,
        "zh-cht": qb,
        "zh-mo": qb,
        "zh-tw": qb,
        "hr-ba": "hr",
        hr: "hr",
        "hr-hr": "hr",
        "cs-cz": "cs",
        cs: "cs",
        "da-dk": "da",
        da: "da",
        "da-gl": "da",
        "nl-aw": X,
        nl: X,
        "nl-be": X,
        "nl-bq": X,
        "nl-cw": X,
        "nl-nl": X,
        "nl-sr": X,
        "nl-sx": X,
        "en-001": a,
        en: a,
        "en-029": a,
        "en-150": a,
        "en-ag": a,
        "en-ai": a,
        "en-as": a,
        "en-at": a,
        "en-au": a,
        "en-bb": a,
        "en-be": a,
        "en-bi": a,
        "en-bm": a,
        "en-bs": a,
        "en-bw": a,
        "en-bz": a,
        "en-ca": a,
        "en-cc": a,
        "en-ch": a,
        "en-ck": a,
        "en-cm": a,
        "en-cx": a,
        "en-cy": a,
        "en-de": a,
        "en-dk": a,
        "en-dm": a,
        "en-er": a,
        "en-fi": a,
        "en-fj": a,
        "en-fk": a,
        "en-fm": a,
        "en-gb": a,
        "en-gd": a,
        "en-gg": a,
        "en-gh": a,
        "en-gi": a,
        "en-gm": a,
        "en-gu": a,
        "en-gy": a,
        "en-hk": a,
        "en-id": a,
        "en-ie": a,
        "en-il": a,
        "en-im": a,
        "en-in": a,
        "en-io": a,
        "en-je": a,
        "en-jm": a,
        "en-ke": a,
        "en-ki": a,
        "en-kn": a,
        "en-ky": a,
        "en-lc": a,
        "en-lr": a,
        "en-ls": a,
        "en-mg": a,
        "en-mh": a,
        "en-mo": a,
        "en-mp": a,
        "en-ms": a,
        "en-mt": a,
        "en-mu": a,
        "en-mw": a,
        "en-my": a,
        "en-na": a,
        "en-nf": a,
        "en-ng": a,
        "en-nl": a,
        "en-nr": a,
        "en-nu": a,
        "en-nz": a,
        "en-pg": a,
        "en-ph": a,
        "en-pk": a,
        "en-pn": a,
        "en-pr": a,
        "en-pw": a,
        "en-rw": a,
        "en-sb": a,
        "en-sc": a,
        "en-sd": a,
        "en-se": a,
        "en-sg": a,
        "en-sh": a,
        "en-si": a,
        "en-sl": a,
        "en-ss": a,
        "en-sx": a,
        "en-sz": a,
        "en-tc": a,
        "en-tk": a,
        "en-to": a,
        "en-tt": a,
        "en-tv": a,
        "en-tz": a,
        "en-ug": a,
        "en-um": a,
        "en-us": a,
        "en-vc": a,
        "en-vg": a,
        "en-vi": a,
        "en-vu": a,
        "en-ws": a,
        "en-za": a,
        "en-zm": a,
        "en-zw": a,
        "et-ee": "et",
        et: "et",
        "fil-ph": "fil",
        fil: "fil",
        "fi-fi": "fi",
        fi: "fi",
        "fr-029": g,
        fr: g,
        "fr-be": g,
        "fr-bf": g,
        "fr-bi": g,
        "fr-bj": g,
        "fr-bl": g,
        "fr-ca": g,
        "fr-cd": g,
        "fr-cf": g,
        "fr-cg": g,
        "fr-ch": g,
        "fr-ci": g,
        "fr-cm": g,
        "fr-dj": g,
        "fr-dz": g,
        "fr-fr": g,
        "fr-ga": g,
        "fr-gf": g,
        "fr-gn": g,
        "fr-gp": g,
        "fr-gq": g,
        "fr-ht": g,
        "fr-km": g,
        "fr-lu": g,
        "fr-ma": g,
        "fr-mc": g,
        "fr-mf": g,
        "fr-mg": g,
        "fr-ml": g,
        "fr-mq": g,
        "fr-mr": g,
        "fr-mu": g,
        "fr-nc": g,
        "fr-ne": g,
        "fr-pf": g,
        "fr-pm": g,
        "fr-re": g,
        "fr-rw": g,
        "fr-sc": g,
        "fr-sn": g,
        "fr-sy": g,
        "fr-td": g,
        "fr-tg": g,
        "fr-tn": g,
        "fr-vu": g,
        "fr-wf": g,
        "fr-yt": g,
        "de-at": Y,
        de: Y,
        "de-be": Y,
        "de-ch": Y,
        "de-de": Y,
        "de-li": Y,
        "de-lu": Y,
        "el-cy": "el",
        el: "el",
        "el-gr": "el",
        "he-il": "he",
        he: "he",
        "hi-in": "hi",
        hi: "hi",
        "hu-hu": "hu",
        hu: "hu",
        "is-is": "is",
        is: "is",
        "id-id": "id",
        id: "id",
        "it-ch": rb,
        it: rb,
        "it-it": rb,
        "it-sm": rb,
        "ja-jp": "ja",
        ja: "ja",
        "sw-cd": lb,
        sw: lb,
        "sw-ke": lb,
        "sw-tz": lb,
        "sw-ug": lb,
        "ko-kp": "ko",
        ko: "ko",
        "ko-kr": "ko",
        "lv-lv": "lv",
        lv: "lv",
        "lt-lt": "lt",
        lt: "lt",
        "mg-mg": "mg",
        mg: "mg",
        "ms-bn": sb,
        ms: sb,
        "ms-my": sb,
        "ms-sg": sb,
        "mt-mt": "mt",
        mt: "mt",
        "nb-no": I,
        nb: I,
        no: I,
        "nb-sj": I,
        "nn-no": I,
        nn: I,
        "fa-ir": "fa",
        fa: "fa",
        "pl-pl": "pl",
        pl: "pl",
        "pt-ao": Q,
        pt: Q,
        "pt-br": Q,
        "pt-cv": Q,
        "pt-gw": Q,
        "pt-mo": Q,
        "pt-mz": Q,
        "pt-pt": Q,
        "pt-st": Q,
        "pt-tl": Q,
        "ro-md": "ro",
        ro: "ro",
        "ro-ro": "ro",
        "ru-by": Z,
        ru: Z,
        "ru-kg": Z,
        "ru-kz": Z,
        "ru-md": Z,
        "ru-ru": Z,
        "ru-ua": Z,
        "sr-cyrl-ba": mb,
        "sr-cyrl": mb,
        "sr-cyrl-me": mb,
        "sr-cyrl-rs": mb,
        "sr-cyrl-xk": mb,
        "sr-latn-ba": nb,
        "sr-latn": nb,
        "sr-latn-me": nb,
        "sr-latn-rs": nb,
        "sr-latn-xk": nb,
        "sk-sk": "sk",
        sk: "sk",
        "sl-si": "sl",
        sl: "sl",
        "es-419": o,
        es: o,
        "es-ar": o,
        "es-bo": o,
        "es-cl": o,
        "es-co": o,
        "es-cr": o,
        "es-cu": o,
        "es-do": o,
        "es-ec": o,
        "es-es": o,
        "es-gq": o,
        "es-gt": o,
        "es-hn": o,
        "es-mx": o,
        "es-ni": o,
        "es-pa": o,
        "es-pe": o,
        "es-ph": o,
        "es-pr": o,
        "es-py": o,
        "es-sv": o,
        "es-us": o,
        "es-uy": o,
        "es-ve": o,
        "sv-ax": tb,
        sv: tb,
        "sv-fi": tb,
        "sv-se": tb,
        "ta-in": ob,
        ta: ob,
        "ta-lk": ob,
        "ta-my": ob,
        "ta-sg": ob,
        "th-th": "th",
        th: "th",
        "to-to": "to",
        to: "to",
        "tr-cy": "tr",
        tr: "tr",
        "tr-tr": "tr",
        "uk-ua": "uk",
        uk: "uk",
        "ur-in": "ur",
        ur: "ur",
        "ur-pk": "ur",
        "vi-vn": "vi",
        vi: "vi",
        "cy-gb": "cy",
        cy: "cy"
    },
    Ab = {
        af: i,
        ar: "الأصلي",
        bn: i,
        "bs-latn": i,
        bg: "Първоначален текст",
        ca: i,
        "zh-chs": "原文",
        "zh-cht": "原始語言",
        yue: i,
        hr: i,
        cs: "Původní",
        da: "Oprindelig",
        nl: "Origineel",
        en: i,
        et: "Lähtetekst",
        fj: i,
        fil: i,
        fi: "Alkuperäinen",
        fr: "Langue source",
        de: i,
        el: "Πρωτότυπο",
        ht: i,
        he: "מקור",
        hi: "मूल",
        mww: i,
        hu: "Eredeti",
        is: i,
        id: "Asli",
        it: "Originale",
        ja: "翻訳元",
        sw: i,
        tlh: i,
        "tlh-qaak": i,
        ko: "원문 언어",
        lv: "Oriģināls",
        lt: "Originalas",
        mg: i,
        ms: i,
        mt: i,
        yua: i,
        no: i,
        otq: i,
        fa: i,
        pl: "Oryginał",
        pt: i,
        ro: i,
        ru: "Исходный текст",
        sm: i,
        "sr-cyrl": i,
        "sr-latn": i,
        sk: "Pôvodný text",
        sl: "Izvirnik",
        es: i,
        sv: i,
        ty: i,
        ta: i,
        th: "ต้นฉบับ",
        to: i,
        tr: i,
        uk: "Оригінал",
        ur: i,
        vi: "Bản gốc",
        cy: i
    };
    window[xb] = Db;
    window["languageDirs"] = vb;
    window["languageMappings"] = db;
    window["localizedOriginal"] = Ab;
    var h = new
    function() {
        var u = "100%",
        m = "0px",
        i = r,
        p = k,
        g = "0",
        a = this,
        z = [66, 77, 0, 0, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        o = [],
        w = [{
            a: "A",
            l: 26
        },
        {
            a: "a",
            l: 26
        },
        {
            a: g,
            l: 10
        },
        {
            a: "+",
            l: 1
        },
        {
            a: s,
            l: 1
        }];
        for (var t = 0; t < w.length; ++t) for (var x = 0; x < w[t].l; ++x) o.push(String.fromCharCode(w[t].a.charCodeAt(0) + x));
        a.addEvent = function(a, c, d, e) {
            var b = function() {
                return d(a, e)
            };
            if (a.addEventListener) a.addEventListener(c, b, p);
            else if (a.attachEvent) a.attachEvent("on" + c, b);
            return b
        };
        a.removeEvent = function(a, c, b) {
            if (a.removeEventListener) a.removeEventListener(c, b, p);
            else if (a.detachEvent) a.detachEvent("on" + c, b)
        };
        var d = a.getStyleValue = function(c, a) {
            if (c.style[a]) return c.style[a];
            if (c.currentStyle) return ! c.currentStyle[a] ? b: c.currentStyle[a];
            if (document.defaultView && document.defaultView.getComputedStyle) {
                a = a.replace(/([A-Z])/g, "-$1").toLowerCase();
                var d = document.defaultView.getComputedStyle(c, b);
                return d && d.getPropertyValue(a)
            }
            return b
        },
        M = a.fixIEQuirks = function(a) {
            if (a.tagName.toLowerCase() === "select") return;
            var f = d(a, "width");
            if (f && f.indexOf(i) > e) a.style.width = parseInt(f) + parseInt(g + d(a, "borderLeftWidth")) + parseInt(g + d(a, "borderRightWidth")) + parseInt(g + d(a, "paddingLeft")) + parseInt(g + d(a, "paddingRight")) + i;
            var c = d(a, "height");
            if (c && c.indexOf(i) > e) a.style.height = parseInt(c) + parseInt(g + d(a, "borderTopWidth")) + parseInt(g + d(a, "borderBottomWidth")) + parseInt(g + d(a, "paddingTop")) + parseInt(g + d(a, "paddingBottom")) + i;
            for (var b = 0; b < a.childNodes.length; ++b) if (a.childNodes[b].nodeType === 1) M(a.childNodes[b])
        };
        a.absXPos = function(a) {
            if (a.getBoundingClientRect) return a.getBoundingClientRect().left + (Math.max(a.ownerDocument.documentElement.scrollLeft, a.ownerDocument.body.scrollLeft) - Math.max(a.ownerDocument.documentElement.clientLeft, a.ownerDocument.documentElement.offsetLeft));
            else return A(a) + C(a)
        };
        function A(a) {
            return a.offsetLeft + (a.offsetParent && a.offsetParent.nodeType == 1 ? A(a.offsetParent) : 0)
        }
        function C(a) {
            return (a.parentNode && a.parentNode.nodeType == 1 ? C(a.parentNode) : 0) + (a.nodeName.toLowerCase() != "html" && a.nodeName.toLowerCase() != "body" && a.scrollLeft ? -a.scrollLeft: 0)
        }
        a.absYPos = function(a) {
            if (a.getBoundingClientRect) return a.getBoundingClientRect().top + (Math.max(a.ownerDocument.documentElement.scrollTop, a.ownerDocument.body.scrollTop) - Math.max(a.ownerDocument.documentElement.clientTop, a.ownerDocument.documentElement.offsetTop));
            else return D(a) + E(a)
        };
        function D(a) {
            return a.offsetTop + (a.offsetParent && a.offsetParent.nodeType == 1 ? D(a.offsetParent) : 0)
        }
        function E(a) {
            return (a.parentNode && a.parentNode.nodeType == 1 ? E(a.parentNode) : 0) + (a.nodeName.toLowerCase() != "html" && a.nodeName.toLowerCase() != "body" && a.scrollTop ? -a.scrollTop: 0)
        }
        a.getVisibleWidth = function(b) {
            var a = n;
            if (window.innerWidth && window.innerWidth > a) a = window.innerWidth;
            else if (b.documentElement.clientWidth && b.documentElement.clientWidth > a) a = b.documentElement.clientWidth;
            else if (b.body.clientWidth && b.body.clientWidth > a) a = b.body.clientWidth;
            return a
        };
        a.getVisibleHeight = function(a) {
            return K(a) ? a.body.clientHeight: a.documentElement.clientHeight
        };
        a.getStringByteCount = function(a) {
            return c.escapeNonAscii ? encodeURIComponent(a).length: encodeURIComponent(a).replace(/%\w\w/g, " ").length
        };
        var I = a.getBlockParent = function(a) {
            var b = a._display = a._display || h.getStyleValue(a, "display"),
            c = a._position = a._position || h.getStyleValue(a, gb);
            return b && b.toLowerCase() == "inline" && c.toLowerCase() == "static" && a.parentNode && a.parentNode.nodeType == 1 ? I(a.parentNode) : a
        },
        K = a.isQuirksMode = function(a) {
            if (a.compatMode && a.compatMode.indexOf("CSS") != e) return p;
            else return l
        },
        F = a.isInternetExplorer11OrHigher = function() {
            var a = p;
            if (navigator.appName == "Netscape") {
                var c = navigator.userAgent,
                b = new RegExp("Trident/.*rv:([0-9]{1,}[.0-9]{0,})");
                if (b.exec(c) != j) {
                    rv = parseFloat(RegExp.$1);
                    if (rv >= 11) a = l
                }
            }
            return a
        },
        O = a.isInternetExplorerAnyVersion = function() {
            var a = y(),
            b = F();
            return a || b
        },
        y = a.isInternetExplorer = function() {
            return window.navigator.userAgent.toUpperCase().indexOf("MSIE") > e
        };
        a.setGradient = function(a, b, c, d) {
            if (!d) d = a.offsetHeight;
            if (a._mstGradCol1 != b.toString() || a._mstGradCol2 != c.toString()) {
                if (a._mstGradElem && a._mstGradElem.parentNode == a) a.removeChild(a._mstGradElem);
                if (b.toString() == c.toString()) a.style.backgroundColor = "#" + b.toString();
                else if (y() && (!document.documentMode || document.documentMode < 8)) H(a, b, c, d);
                else {
                    a.style.backgroundRepeat = "repeat-x";
                    a.style.backgroundImage = "url('data:image/x-ms-bmp;base64," + J(G(b, c, d)) + "')"
                }
                a._mstGradCol1 = b.toString();
                a._mstGradCol2 = c.toString()
            }
        };
        function H(a, b, c, f) {
            var e = ",endColorStr=#FF",
            d = "progid:DXImageTransform.Microsoft.Gradient(startColorStr=#FF";
            a._mstGradElem = document.createElement(B);
            a._mstGradElem.style.fontSize = m;
            a._mstGradElem.style.width = u;
            a._mstGradElem.style.height = f + i;
            a._mstGradElem.style.marginBottom = "-" + a._mstGradElem.style.height;
            a.insertBefore(a._mstGradElem, a.firstChild);
            a._mstGradElem.appendChild(document.createElement(B));
            a._mstGradElem.appendChild(document.createElement(B));
            a._mstGradElem.firstChild.style.width = a._mstGradElem.lastChild.style.width = u;
            a._mstGradElem.firstChild.style.height = a._mstGradElem.lastChild.style.height = f / 2 + i;
            a._mstGradElem.firstChild.style.fontSize = a._mstGradElem.lastChild.style.fontSize = m;
            a._mstGradElem.firstChild.style.filter = d + c + e + c.interpolate(b, .5) + ")";
            a._mstGradElem.lastChild.style.filter = d + b + e + b.interpolate(c, .5) + ")"
        }
        function G(f, g, c) {
            var e = 1 * c,
            a = [];
            for (var b = 0; b < z.length; ++b) a.push(z[b]);
            q(a, 2, 54 + e * 4);
            q(a, 18, 1);
            q(a, 22, c);
            q(a, 34, e * 4);
            for (var b = 0; b < c; ++b) {
                var d = b < c / 2 ? f.interpolate(g, .5 - b / c) : f.interpolate(g, b / c);
                a.push(d.b);
                a.push(d.g);
                a.push(d.r);
                a.push(v)
            }
            return a
        }
        function q(a, b, c) {
            a.splice(b, 1, c & v);
            a.splice(b + 1, 1, c >>> 8 & v);
            a.splice(b + 2, 1, c >>> f & v);
            a.splice(b + 3, 1, c >>> 24 & v)
        }
        a.applyProtectiveCss = function(a) {
            var d = "content-box",
            c = "normal",
            b = L;
            a.style.backgroundAttachment = "scroll";
            a.style.backgroundColor = "Transparent";
            a.style.backgroundImage = b;
            a.style.color = "White";
            a.style.fontStyle = c;
            a.style.fontVariant = c;
            a.style.fontWeight = c;
            a.style.letterSpacing = c;
            a.style.lineHeight = c;
            a.style.margin = m;
            a.style.outline = b;
            a.style.overflow = "visible";
            a.style.padding = m;
            a.style.verticalAlign = "baseline";
            a.style.wordSpacing = c;
            a.style.fontFamily = '"Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif';
            try {
                a.style.fontSize = "inherit"
            } catch(e) {
                a.style.fontSize = u
            }
            a.style.textTransform = b;
            a.style.textDecoration = b;
            a.style.border = m;
            a.style.boxSizing = d;
            a.style.MozBoxSizing = d;
            a.style.float = b;
            a.style.maxWidth = b
        };
        function J(c) {
            var e = 1048576,
            d = [];
            while (c.length) {
                var a = [];
                a.push(c.shift());
                d.push(o[a[0] >> 2 & 63]);
                a.push(c.length > 0 ? c.shift() : e);
                a.push(c.length > 0 ? c.shift() : e);
                d.push(o[(a[0] << 4 | a[1] >>> 4) & 63]);
                d.push(a[1] == e ? "=": o[(a[1] << 2 | a[2] >>> 6) & 63]);
                d.push(a[2] == e ? "=": o[a[2] & 63])
            }
            return d.join(b)
        }
        var N = a.clone = function(a) {
            var c = {};
            for (var b in a) if (typeof a[b] === "object" && a !== j) c[b] = this.clone(a);
            else c[b] = a[b];
            return c
        };
        a.compress = function(i) {
            var d = {},
            g = 0,
            h = 0,
            a = b,
            c, e, f = [];
            while (c = i.charAt(h++)) {
                d[c] = c.charCodeAt(0);
                e = a + c;
                if (d[e]) a = e;
                else {
                    d[e] = --g;
                    f.push(d[a]);
                    a = c
                }
            }
            if (a) f.push(d[a]);
            return f
        };
        a.decompress = function(f) {
            var d = {},
            e = 0,
            g = 0,
            c = String.fromCharCode(f[g++]),
            a,
            b,
            h = c;
            while (a = f[g++]) {
                if (a > 0) d[a] = String.fromCharCode(a);
                if (d[a]) b = d[a];
                else if (a + 1 == e) b = c + c.charAt(0);
                else throw "Invalid input data";
                h += b;
                d[--e] = c + b.charAt(0);
                c = b
            }
            return h
        };
        return a
    };
    function C(e, d, c) {
        var a = this;
        a.r = e;
        a.g = d;
        a.b = c;
        for (var b in a) a[b] = a[b] > v ? v: a[b] < 0 ? 0 : a[b];
        a.toString = function() {
            var c = "0" + this.r.toString(f),
            b = "0" + this.g.toString(f),
            a = "0" + this.b.toString(f);
            return (c.substr(c.length - 2) + b.substr(b.length - 2) + a.substr(a.length - 2)).toUpperCase()
        };
        a.interpolate = function(b, c) {
            var a = this;
            if (a.toString() == b.toString()) return new C(a.r, a.g, a.b);
            return new C(Math.round(a.r + c * (b.r - a.r)), Math.round(a.g + c * (b.g - a.g)), Math.round(a.b + c * (b.b - a.b)))
        };
        return a
    }
    C.parse = function(a) {
        var b = a.match(/rgb\((\d+)\s*,\s*(\d+)\s*,\s*(\d+)\)/i);
        if (b) return new C(parseInt(b[1], 10), parseInt(b[2], 10), parseInt(b[3], 10));
        a = a.split(" ")[0];
        if (a.substr(0, 1) == "#") {
            if (a.length == 4) return new C(f * parseInt(a.substr(1, 1), f), f * parseInt(a.substr(2, 1), f), f * parseInt(a.substr(3, 1), f));
            else if (a.length == 7) return new C(parseInt(a.substr(1, 2), f), parseInt(a.substr(3, 2), f), parseInt(a.substr(5, 2), f))
        } else if (C.nameTable[a.toLowerCase()]) return C.parse(C.nameTable[a.toLowerCase()]);
        else throw "Color format not suported: " + a;
    };
    C.nameTable = {
        Black: "#000000",
        Navy: "#000080",
        DarkBlue: "#00008B",
        MediumBlue: "#0000CD",
        Blue: "#0000FF",
        DarkGreen: "#006400",
        Green: "#008000",
        Teal: "#008080",
        DarkCyan: "#008B8B",
        DeepSkyBlue: "#00BFFF",
        DarkTurquoise: "#00CED1",
        MediumSpringGreen: "#00FA9A",
        Lime: "#00FF00",
        SpringGreen: "#00FF7F",
        Aqua: "#00FFFF",
        Cyan: "#00FFFF",
        MidnightBlue: "#191970",
        DodgerBlue: "#1E90FF",
        LightSeaGreen: "#20B2AA",
        ForestGreen: "#228B22",
        SeaGreen: "#2E8B57",
        DarkSlateGray: "#2F4F4F",
        LimeGreen: "#32CD32",
        MediumSeaGreen: "#3CB371",
        Turquoise: "#40E0D0",
        RoyalBlue: "#4169E1",
        SteelBlue: "#4682B4",
        DarkSlateBlue: "#483D8B",
        MediumTurquoise: "#48D1CC",
        "Indigo ": "#4B0082",
        DarkOliveGreen: "#556B2F",
        CadetBlue: "#5F9EA0",
        CornflowerBlue: "#6495ED",
        MediumAquaMarine: "#66CDAA",
        DimGray: "#696969",
        SlateBlue: "#6A5ACD",
        OliveDrab: "#6B8E23",
        SlateGray: "#708090",
        LightSlateGray: "#778899",
        MediumSlateBlue: "#7B68EE",
        LawnGreen: "#7CFC00",
        Chartreuse: "#7FFF00",
        Aquamarine: "#7FFFD4",
        Maroon: "#800000",
        Purple: "#800080",
        Olive: "#808000",
        Gray: "#808080",
        SkyBlue: "#87CEEB",
        LightSkyBlue: "#87CEFA",
        BlueViolet: "#8A2BE2",
        DarkRed: "#8B0000",
        DarkMagenta: "#8B008B",
        SaddleBrown: "#8B4513",
        DarkSeaGreen: "#8FBC8F",
        LightGreen: "#90EE90",
        MediumPurple: "#9370D8",
        DarkViolet: "#9400D3",
        PaleGreen: "#98FB98",
        DarkOrchid: "#9932CC",
        YellowGreen: "#9ACD32",
        Sienna: "#A0522D",
        Brown: "#A52A2A",
        DarkGray: "#A9A9A9",
        LightBlue: "#ADD8E6",
        GreenYellow: "#ADFF2F",
        PaleTurquoise: "#AFEEEE",
        LightSteelBlue: "#B0C4DE",
        PowderBlue: "#B0E0E6",
        FireBrick: "#B22222",
        DarkGoldenRod: "#B8860B",
        MediumOrchid: "#BA55D3",
        RosyBrown: "#BC8F8F",
        DarkKhaki: "#BDB76B",
        Silver: "#C0C0C0",
        MediumVioletRed: "#C71585",
        "IndianRed ": "#CD5C5C",
        Peru: "#CD853F",
        Chocolate: "#D2691E",
        Tan: "#D2B48C",
        LightGrey: "#D3D3D3",
        PaleVioletRed: "#D87093",
        Thistle: "#D8BFD8",
        Orchid: "#DA70D6",
        GoldenRod: "#DAA520",
        Crimson: "#DC143C",
        Gainsboro: "#DCDCDC",
        Plum: "#DDA0DD",
        BurlyWood: "#DEB887",
        LightCyan: "#E0FFFF",
        Lavender: "#E6E6FA",
        DarkSalmon: "#E9967A",
        Violet: "#EE82EE",
        PaleGoldenRod: "#EEE8AA",
        LightCoral: "#F08080",
        Khaki: "#F0E68C",
        AliceBlue: "#F0F8FF",
        HoneyDew: "#F0FFF0",
        Azure: "#F0FFFF",
        SandyBrown: "#F4A460",
        Wheat: "#F5DEB3",
        Beige: "#F5F5DC",
        WhiteSmoke: "#F5F5F5",
        MintCream: "#F5FFFA",
        GhostWhite: "#F8F8FF",
        Salmon: "#FA8072",
        AntiqueWhite: "#FAEBD7",
        Linen: "#FAF0E6",
        LightGoldenRodYellow: "#FAFAD2",
        OldLace: "#FDF5E6",
        Red: "#FF0000",
        Fuchsia: "#FF00FF",
        Magenta: "#FF00FF",
        DeepPink: "#FF1493",
        OrangeRed: "#FF4500",
        Tomato: "#FF6347",
        HotPink: "#FF69B4",
        Coral: "#FF7F50",
        Darkorange: "#FF8C00",
        LightSalmon: "#FFA07A",
        Orange: "#FFA500",
        LightPink: "#FFB6C1",
        Pink: "#FFC0CB",
        Gold: "#FFD700",
        PeachPuff: "#FFDAB9",
        NavajoWhite: "#FFDEAD",
        Moccasin: "#FFE4B5",
        Bisque: "#FFE4C4",
        MistyRose: "#FFE4E1",
        BlanchedAlmond: "#FFEBCD",
        PapayaWhip: "#FFEFD5",
        LavenderBlush: "#FFF0F5",
        SeaShell: "#FFF5EE",
        Cornsilk: "#FFF8DC",
        LemonChiffon: "#FFFACD",
        FloralWhite: "#FFFAF0",
        Snow: "#FFFAFA",
        Yellow: "#FFFF00",
        LightYellow: "#FFFFE0",
        Ivory: "#FFFFF0",
        White: "#FFFFFF"
    };
    new
    function() {
        var a = {};
        for (var b in C.nameTable) a[b.toLowerCase()] = C.nameTable[b];
        C.nameTable = a
    };
    function Eb(Jb, nb, rb, ob, db, Gc, Cc, Q, J, Lc) {
        var ic = "Element too deep",
        K = "b",
        Kb = "LP",
        tb = H,
        v = b,
        g = j,
        bc = "scroll",
        ac = N,
        hb = " ",
        r = k,
        i = l,
        y = this,
        m = y,
        L = rb,
        P = ob,
        o = nb,
        Wb = db,
        vc = Gc,
        lb = Cc,
        Qb = [],
        I,
        bb,
        pc = J ? i: r,
        ib = i,
        Ec;
        window.Microsoft.Translator.APIRequests = 0;
        window.Microsoft.Translator.APIResponses = 0;
        window.Microsoft.Translator.translationCallsTime = [];
        window.Microsoft.Translator.totalTranslationTime = 0;
        var Bb, Lb = !Lc && !J,
        F = 0,
        G = 9,
        Y = 0,
        hc = 15,
        gc = w;
        if (navigator.userAgent && (navigator.userAgent.indexOf("Chrome") > e || navigator.userAgent.indexOf("Mobile") > e)) {
            hc /= 3;
            G /= 2;
            gc /= 3;
            Y = 200
        }
        lb = lb * G;
        var Pb = [],
        Rb = [],
        s = {};
        s.size = 0;
        var yb = [],
        T;
        c.requestGroup = Math.floor(Math.random() * 1e9).toString(f);
        c.from = rb;
        c.to = ob;
        if (nb.nodeType != 1) throw new Error("Invalid input type");
        if (rb == ob) {
            Ob(1);
            if (db) db(nb);
            return y
        }
        if (!o.innerHTML || !o.innerText && !o.textContent) {
            if (db) db(nb);
            return y
        }
        var mb, cb, fb = 1400,
        sc = 1600,
        rc = (o.innerText || o.textContent).replace(/\s+/g, hb),
        Fb = 0,
        Sb = 0,
        xb = o.innerHTML.length,
        lc = 0,
        a = [o],
        X = [0],
        q = [{
            o: xb,
            p: 0
        }],
        Ub = [],
        S = [],
        nc = [],
        B = [],
        jb = [],
        Hb = r,
        eb = r,
        qc = r,
        Ib = r;
        y.text = rc;
        y.textLength = rc.length;
        y.showTooltips = i;
        y.showHighlight = i;
        y.sourceFrame = Q ? i: r;
        y.detectedLanguage;
        y.transItems = [];
        var z = [],
        Db,
        Gb = 0,
        qb = 0;
        if (ib && o.ownerDocument && o.ownerDocument.documentElement && o == o.ownerDocument.documentElement) {
            var wc = o.ownerDocument.getElementsByTagName(ac)[0];
            if (wc) {
                xb -= wc.innerHTML.length;
                q[0].o = xb
            }
        }
        if (window.translatorOnBegin || document.translatorOnBegin) try { (window.translatorOnBegin || document.translatorOnBegin)()
        } catch(Ec) {}
        function Zb() {
            qc = i;
            if (Ib) {
                Ib = r;
                if (s.size < G) if (J && Q) C();
                else setTimeout(function() {
                    C()
                },
                Y)
            }
        }
        h.addEvent(o.ownerDocument.defaultView || o.ownerDocument.parentWindow, bc, Zb);
        var Fc = y.cancel = function() {
            if (Microsoft.TranslatorOverride && Microsoft.TranslatorOverride.hideTooltip) Microsoft.TranslatorOverride.hideTooltip();
            if (!o) return;
            Hb = i;
            if (mb) mb.abort("canceled by user.");
            Tb(o);
            o = g
        };
        try {
            if (!toolbar || !toolbar.addExitEvent || !toolbar.setProgress || !toolbar.setLanguagePair) toolbar = g
        } catch(Ic) {
            toolbar = g
        }
        var Oc = y.exit = function() {
            Fc();
            if (toolbar) toolbar.hide()
        };
        function oc(a) {
            a = Math.max(a, 0);
            a = Math.min(a, w);
            for (var b = 0; b < Qb.length; ++b) Qb[b](a)
        }
        y.addProgressEvent = function(a) {
            Qb.push(a)
        };
        if (!m.sourceFrame) if (toolbar && toolbar.setProgress) m.addProgressEvent(toolbar.setProgress);
        y.setParallel = function(a) {
            I = a
        };
        if (toolbar) {
            toolbar.addExitEvent(y.exit);
            toolbar.setProgress(0);
            toolbar.setLanguagePair(L, P)
        }
        var p = {
            Inherit: 0,
            On: 1,
            Off: 2,
            Skip: 3
        },
        fc = {
            google: {
                value: {
                    notranslate: p.Off
                },
                content: {
                    notranslate: p.Off
                }
            },
            microsoft: {
                value: {
                    notranslate: p.Off
                },
                content: {
                    notranslate: p.Off
                }
            }
        },
        dc = {
            translate: {
                "true": p.On,
                yes: p.On,
                "false": p.Off,
                no: p.Off,
                skip: p.Skip
            }
        },
        ec = {
            notranslate: p.Off,
            skiptranslate: p.Skip
        };
        if (Kc(o) == p.Off) {
            if (db) db(nb);
            return
        }
        a.top = X.top = q.top = function() {
            return this[this.length - 1]
        };
        var cc = {
            head: 1,
            script: 1,
            style: 1,
            code: 1,
            samp: 1,
            "var": 1,
            kbd: 1,
            pre: 1,
            input: 1,
            object: 1,
            address: 1,
            textarea: 1,
            noscript: 1
        },
        sb = {
            hr: 1,
            option: 1,
            title: 1,
            br: 1,
            frame: 1,
            iframe: 1
        };
        for (var Dc in cc) sb[Dc] = 1;
        delete sb["code"];
        delete sb["samp"];
        delete sb["var"];
        function Ab(b) {
            var a;
            if (vb[b] == t) a = {
                direction: t,
                textAlign: R
            };
            else a = {
                direction: d,
                textAlign: D
            };
            return a
        }
        if (!Q && !J) bb = Ab(ob);
        function zc() {
            var c = [];
            for (var b = a.length - 2; b >= 0; --b) if (a[b].id) {
                c.unshift(a[b].id.toString());
                break
            } else c.unshift((a[b].nodeName && a[b].nodeName.toLowerCase ? a[b].nodeName.toLowerCase() : v) + "-" + X[b].toString());
            return c.join("_")
        }
        function C() {
            var b = "len";
            if (c.maxChars && c.maxChars < lc && !qc && !m.sourceFrame) {
                if (!Ib) {
                    xc();
                    Ib = i
                }
                return
            }
            var f = [],
            e = r,
            j = g;
            if (ib && q.length) {
                var n = 0;
                for (var k = 0; k < q.length; ++k) n += parseInt(q[k].p);
                oc(Math.min(99.999 * (n - Sb) / (xb - Sb), 99.999))
            }
            while (a.length > 0 && (Fb < fb || f.length)) {
                if (a.length && tc(a.top()) && ab(a.top())) {
                    a.push(a.top().contentWindow.document.documentElement);
                    X.push(0);
                    q.push({
                        o: 0,
                        p: 0
                    });
                    e = i;
                    h.addEvent(a.top().ownerDocument.defaultView || a.top().ownerDocument.parentWindow, bc, Zb);
                    if (ib) {
                        var d = typeof a.top().length == tb ? a.top().length: a.top().getAttribute(b) || (a.top().innerHTML && a.top().innerHTML.length ? a.top().innerHTML.length: 0);
                        try {
                            if (!a.top().length && !a.top().getAttribute(b)) a.top().setAttribute(b, d)
                        } catch(l) {}
                        q[q.length - 1].o = d;
                        xb += d
                    }
                } else if (a.length && a.top().firstChild && a.top().firstChild.parentNode == a.top() && !kb(a.top()) && ab(a.top())) {
                    a.push(a.top().firstChild);
                    X.push(0);
                    q.push({
                        o: 0,
                        p: 0
                    });
                    e = i;
                    if (ib) {
                        var d = typeof a.top().length == tb ? a.top().length: a.top().getAttribute(b) || (a.top().innerHTML && a.top().innerHTML.length ? a.top().innerHTML.length: 0);
                        try {
                            if (!a.top().length && !a.top().getAttribute(b)) a.top().setAttribute(b, d)
                        } catch(l) {}
                        q[q.length - 1].o = d
                    }
                } else {
                    while (a.length && (!a.top().nextSibling && !a.top().nextElementSibling)) {
                        a.pop();
                        X.pop();
                        q.pop();
                        e = i
                    }
                    if (a.length > 1) {
                        if (ib && a.top().nodeName.toLowerCase() != ac) q[q.length - 2].p += parseInt(q[q.length - 1].o);
                        a.push(a.pop().nextSibling);
                        q[q.length - 1] = {
                            o: 0,
                            p: 0
                        };
                        if (!kb(a.top())) e = i;
                        if (ib) {
                            var d = typeof a.top().length == tb ? a.top().length: a.top().getAttribute(b) || (a.top().innerHTML && a.top().innerHTML.length ? a.top().innerHTML.length: 0);
                            try {
                                if (!a.top().length && !a.top().getAttribute(b)) a.top().setAttribute(b, d)
                            } catch(l) {}
                            q[q.length - 1].o = d
                        }
                    } else {
                        a.pop();
                        X.pop();
                        q.pop();
                        e = i
                    }
                }
                if (e || a.length > 0 && !kb(a.top())) {
                    if (f.length) try {
                        yc(f, j)
                    } catch(p) {
                        if (c.debug);
                    }
                    e = r;
                    j = g
                }
                if (a.length) {
                    if (a.top().clientHeight < a.top().scrollHeight) h.addEvent(a.top(), bc, Zb);
                    if (kb(a.top())) {
                        if (!j) j = zc(); ++X[X.length - 1];
                        f.push(a.top())
                    }
                    if (a.top().nodeName.toLowerCase() != ac && !ab(a.top())) Sb += parseInt(q.top().o);
                    Vb(a.top())
                }
            }
            if (Fb > 0 || yb.length > 0) Ac();
            else {
                if (s.size > 0 || yb.length > 0) return;
                oc(w);
                Ob(1);
                if (Wb) Wb(o);
                Wb = g;
                if (Microsoft.TranslatorOverride && Microsoft.TranslatorOverride.showHighlight) Microsoft.TranslatorOverride.showHighlight(m, L, P);
                if (window.translatorOnComplete || document.translatorOnComplete) try { (window.translatorOnComplete || document.translatorOnComplete)()
                } catch(p) {
                    if (c.debug);
                }
                xc()
            }
        }
        function Vb(a) {
            var f = "adjustalign";
            try {
                if (!a.getAttribute) return;
                a.adjustAlign = a.getAttribute(f) && !(a.getAttribute(f).toLowerCase() == U);
                if (a.adjustAlign == g) a.adjustAlign = a.parentNode.adjustAlign;
                if (a.adjustAlign == undefined || a.adjustAlign == g) a.adjustAlign = i;
                if (bb && a && a.style && ab(a) && !m.sourceFrame && c.service != Kb && a.adjustAlign) for (var b in bb) try {
                    var d = h.getStyleValue(a, b);
                    if (d != bb[b]) {
                        if (b == "textAlign" && (d && d.toLowerCase().indexOf(E) != e || a.tagName && a.tagName.toLowerCase() == E)) continue;
                        if (Lb) {
                            if (!a._mstStyle) a._mstStyle = {};
                            if (a.style[b]) a._mstStyle[b] = a.style[b];
                            else a._mstStyle[b] = d
                        }
                        a.style[b] = bb[b]
                    }
                } catch(j) {
                    console.error(j)
                }
            } catch(k) {
                console.error(k)
            }
        }
        function Ob(e) {
            var b = "_mssrc";
            if (!T) if (o.getElementsByTagName) T = o.getElementsByTagName(x);
            else if (o.documentElement.getElementsByTagName) T = o.documentElement.getElementsByTagName(x);
            else if (o.ownerDocument.documentElement.getElementsByTagName) T = o.ownerDocument.documentElement.getElementsByTagName(x);
            var a;
            if (T && T.length > 0) var d = 0;
            for (var c = 0; c < T.length && d < Math.max(1, T.length * e); c++) {
                a = T[c];
                if (a.getAttribute(b)) {
                    a.src = a.getAttribute(b);
                    a.removeAttribute(b);
                    d++
                }
            }
        }
        function xc() {
            if (!I || !m.sourceFrame) {
                var b = [];
                b.push("svc=" + encodeURIComponent(c.service));
                b.push("loc=" + encodeURIComponent(c.locale));
                b.push("ref=" + encodeURIComponent(c.ref));
                b.push("from=" + encodeURIComponent(L ? L: v));
                b.push("to=" + encodeURIComponent(P ? P: v));
                b.push("dtc=" + encodeURIComponent(m.detectedLanguage ? m.detectedLanguage: v));
                var d = jb.join(" | "),
                f = h.getStringByteCount(d);
                if (f > 128) d = d.substr(0, Math.round(d.length * 128 / f)) + "...";
                b.push("text=" + wb(d ? d: v));
                var o = z.length,
                n = 0,
                j = 0,
                g = 0,
                l = 0,
                i = 0,
                e = 0,
                k = 0;
                for (var a = 0; a < z.length && a < 64; ++a) {
                    b.push(a.toString() + "=" + [z[a].r, z[a].c, z[a].s, z[a].e, z[a].l].join("_"));
                    if (z[a].r === "E") n++;
                    if (z[a].l > e) e = z[a].l;
                    g += z[a].c;
                    j += z[a].s;
                    l += z[a].e;
                    i += z[a].l
                }
                k = e;
                if (window.mscc === undefined || window.mscc.hasConsent()) {
                    if (typeof telemetry !== A && telemetry) telemetry.trackMetric([{
                        metric: "translation.calls",
                        value: o
                    },
                    {
                        metric: "translation.errors",
                        value: n
                    },
                    {
                        metric: "translation.characters",
                        value: g
                    },
                    {
                        metric: "translation.sentences",
                        value: j
                    },
                    {
                        metric: "tranlation.elements",
                        value: l
                    },
                    {
                        metric: "translation.latencies",
                        value: i
                    },
                    {
                        metric: "translation.time",
                        value: k
                    },
                    {
                        metric: "translation.to",
                        value: P
                    },
                    {
                        metric: "translation.from",
                        value: L
                    }]);
                    pb("/sync.ashx?" + b.join(u))
                }
                jb = [];
                z = []
            }
        }
        function ab(a) {
            if (a.nodeType == 3) return i;
            if (a.nodeType != 1) return r;
            if (!a.hasChildNodes() && !tc(a)) return r;
            var b;
            try {
                b = jc(a)
            } catch(d) {
                if (c.debug);
            }
            if (b == p.Off || b == p.Skip) return r;
            if (cc[a.nodeName.toLowerCase()]) return r;
            if (!a.innerHTML || !Mb(a.innerHTML)) return r;
            return i
        }
        function kb(a) {
            if (a.nodeType == 3) return i;
            else if (a.nodeType != 1 || a._mstChunk || h.getStyleValue(a, "display").toLowerCase() != "inline" || h.getStyleValue(a, gb).toLowerCase() != "static" || sb[a.nodeName.toLowerCase()]) return r;
            for (var b = 0; b < a.childNodes.length; ++b) if (!kb(a.childNodes[b])) return r;
            return i
        }
        function tc(a) {
            try {
                if (a.contentWindow && a.contentWindow.document && a.contentWindow.document.documentElement) return i
            } catch(b) {
                if (c.debug);
            }
            return r
        }
        function jc(b) {
            var a = p.Inherit;
            if (!b.getAttribute) return a;
            for (var h in dc) {
                var e = ub(b, h);
                if (e != g) {
                    var j = dc[h],
                    f = j[e.toString().toLowerCase()];
                    a = f || a;
                    if (a == p.Off || a == p.Skip) return a
                }
            }
            var d = ub(b, "class") || ub(b, "className");
            if (d != g) {
                var i = d.toString().split(hb);
                for (var c = 0; c < i.length; c++) {
                    var k = i[c],
                    f = ec[k.toLowerCase()];
                    a = f || a;
                    if (a == p.Off) return a
                }
            }
            return a
        }
        function ub(b, a) {
            try {
                return b.getAttribute(a) || b[a]
            } catch(d) {
                if (c.debug);
                return g
            }
        }
        function Kc(m) {
            var b = p.Inherit,
            k = m.ownerDocument.getElementsByTagName("meta");
            for (var d = 0; d < k.length; d++) {
                var l = k[d],
                c = ub(l, "name");
                if (c != g) if (fc[c.toString().toLowerCase()] != g) {
                    var f = fc[c.toString().toLowerCase()];
                    for (var h in f) {
                        var a = ub(l, h);
                        if (a != g) {
                            a = a.toString().toLowerCase();
                            var i = f[h][a];
                            if (i != g) {
                                b = i || b;
                                if (b == p.Off) return b
                            }
                            if (a.match(/^notranslateclasses\s/i)) {
                                var j = a.split(/\s+/);
                                for (var e = 1; e < j.length; e++) ec[j[e]] = p.Off
                            }
                        }
                    }
                }
            }
            return b
        }
        function yc(d, e) {
            Bc(d);
            var a = Yb(d);
            if (a && ab(a)) {
                a._mstHash = Hc(e);
                while (m[a._mstHash])++a._mstHash;
                m[a._mstHash] = a;
                if (pc && !m.sourceFrame) if (pc && I && I[a._mstHash]) {
                    var b = W(I[a._mstHash], K),
                    f = W(a, K);
                    if (b.split(/<b\d+/g).length != f.split(/<b\d+/g).length) {
                        if (c.debug);
                        return
                    }
                } else {
                    if (c.debug);
                    return
                } else var b = W(a, K);
                if (Mb(b)) {
                    Fb += h.getStringByteCount(b);
                    Ub.push(a);
                    S.push(b)
                }
            }
        }
        function Yb(a) {
            var b = g;
            if (a.length > 0) if (a.length == 1 && a[0].nodeType == 1) b = a.pop();
            else if (a[0].parentNode && a.length == a[0].parentNode.childNodes.length) {
                b = a.pop().parentNode;
                while (a.length > 0) a.pop()
            } else {
                b = a[0].ownerDocument.createElement(V);
                b._mstChunk = i;
                if (a[0].parentNode) a[0].parentNode.insertBefore(b, a[0]);
                while (a.length > 0) b.appendChild(a.shift())
            }
            return b
        }
        function Bc(a) {
            var c = i;
            while (c) {
                c = r;
                if (a.length == 1 && !ab(a[0])) return;
                if (a.length == 1 && a[0].nodeType == 1 && a[0].childNodes.length > 0) {
                    var e = a.pop();
                    for (var d = 0; d < e.childNodes.length; d++) a.push(e.childNodes[d]);
                    c = i
                }
                if (a.length > 0) if (!Xb(a[0])) {
                    var b = a.shift();
                    if (b.nodeType == 3 && !b.nodeValue) b.parentNode.removeChild(b);
                    c = i
                } else if (!Xb(a[a.length - 1])) {
                    var b = a.pop();
                    if (b.nodeType == 3 && !b.nodeValue) b.parentNode.removeChild(b);
                    c = i
                }
            }
            if (a.length == 1 && !Xb(a[0])) a.pop()
        }
        function Mb(a) {
            return !! (a.match(/[a-zA-Z0-9\xC0-\uFFFF]/) || J && a.replace(/[\r\n\s]/g, v).length > 0)
        }
        function Xb(a) {
            if (!kb(a)) return i;
            var b = v;
            switch (a.nodeType) {
            case 1:
                b = a.innerText || a.textContent || v;
                break;
            case 3:
                b = a.nodeValue || v
            }
            if (b.match(/^[\s\xA0]*$/)) return r;
            if (Mb(b)) return i;
            return r
        }
        function W(a, i, e) {
            e = e ? e: 1;
            if (e > 9) throw new Error(ic);
            var d = [],
            f = 0,
            l = 0;
            for (var b = 0; b < a.childNodes.length; ++b) switch (a.childNodes[b].nodeType) {
            case 1:
                var j = i + e.toString() + f.toString();
                try {
                    var g = jc(a.childNodes[b])
                } catch(k) {
                    if (c.debug);
                }
                if (g == p.Skip && a.childNodes[b].previousSibling && a.childNodes[b].previousSibling.nodeType == 1) a.childNodes[b].previousSibling._mstSkipNext = f;
                else if (g == p.Skip && a.childNodes[b].nextSibling && a.childNodes[b].nextSibling.nodeType == 1) a.childNodes[b].nextSibling._mstSkipPrev = f;
                else {
                    d.push("<");
                    d.push(j);
                    d.push(">");
                    if (g != p.Skip) d.push(W(a.childNodes[b], i, e + 1));
                    d.push("</");
                    d.push(j);
                    d.push(">")
                }++f;
                break;
            case 3:
                if (a.childNodes[b].nodeValue) {
                    var h = a.childNodes[b].nodeValue.replace(/[\s\xA0]+/g, hb);
                    if (h != a.childNodes[b].nodeValue) a.replaceChild(a.ownerDocument.createTextNode(h), a.childNodes[b]);
                    d.push(Nc(h))
                }
            }
            return d.join(v)
        }
        function Z(a, f, j, b, c, h) {
            if (!h) h = 1;
            if (h > 9) throw new Error(ic);
            var k = [];
            for (var i = 0; i < a.childNodes.length; ++i) {
                if (a.childNodes[i].parentNode != a) a.appendChild(a.childNodes[i--]);
                if (a.childNodes[i].nodeType == 1) k.push(a.childNodes[i])
            }
            var e = 0,
            d = 0;
            f.replace(new RegExp("<" + j + h + "(\\d+)>(.*)<\\/" + j + h + "\\1>", "gi"),
            function(m, q, o, l) {
                while (b && b[0] <= l - e) {
                    var n = a.ownerDocument.createTextNode(Eb(f.substr(e, b[0])));
                    c[c.length - 1].push(n);
                    c.push([]);
                    a.insertBefore(n, d < a.childNodes.length ? a.childNodes[d] : g); ++d;
                    e += b[0];
                    b.shift()
                }
                if (e < l) {
                    var n = a.ownerDocument.createTextNode(Eb(f.substr(e, l - e)));
                    if (b) {
                        c[c.length - 1].push(n);
                        b[0] -= l - e
                    }
                    a.insertBefore(n, d < a.childNodes.length ? a.childNodes[d] : g); ++d;
                    e = l
                }
                var i = k[parseInt(q)];
                if (i != a.childNodes[d]) a.insertBefore(i, a.childNodes[d]); ++d;
                if (typeof i._mstSkipPrev == tb) {
                    var s = k[i._mstSkipPrev];
                    a.insertBefore(s, i); ++d;
                    if (b) c[c.length - 1].push(s);
                    i._mstSkipPrev = v
                }
                if (ab(i)) if (b) if (b[0] < m.length) {
                    c.push([]);
                    b[0] -= 4 + q.length;
                    Z(i, o, j, b, c, h + 1);
                    b[0] -= 5 + q.length
                } else {
                    c[c.length - 1].push(i);
                    Z(i, o, j, g, g, h + 1);
                    b[0] -= m.length
                } else Z(i, o, j, g, g, h + 1);
                else if (b) {
                    if (b[0] < m.length) c.push([i], []);
                    else c[c.length - 1].push(i);
                    for (var p = m.length; p > b[0]; b.shift()) p -= b[0];
                    b[0] -= p
                }
                if (typeof i._mstSkipNext == tb) {
                    var r = k[i._mstSkipNext];
                    a.insertBefore(r, i.nextSibling); ++d;
                    if (b) c[c.length - 1].push(r);
                    i._mstSkipNext = v
                }
                e += m.length
            });
            while (b && b[0] <= f.length - e) {
                var l = a.ownerDocument.createTextNode(Eb(f.substr(e, b[0])));
                c[c.length - 1].push(l);
                c.push([]);
                a.insertBefore(l, d < a.childNodes.length ? a.childNodes[d] : g); ++d;
                e += b[0];
                b.shift()
            }
            if (e < f.length) {
                var l = a.ownerDocument.createTextNode(Eb(f.substr(e, f.length - e)));
                a.insertBefore(l, d < a.childNodes.length ? a.childNodes[d] : g); ++d;
                if (b) {
                    c[c.length - 1].push(l);
                    b[0] -= f.length - e
                }
            }
            while (d < a.childNodes.length) a.removeChild(a.childNodes[d]);
            if (c && c[c.length - 1].length) c.push([])
        }
        function Nc(a) {
            if (c.service == Kb && Default.Globals.PhraseAlignment) return a.replace(/[\s\xA0]/g, hb);
            else return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/[\s\xA0]/g, hb)
        }
        function Eb(a) {
            if (c.service == Kb && Default.Globals.PhraseAlignment) return a;
            else return a.replace(/<\w+>/g, v).replace(/<\/\w+>/g, v).replace(/&gt;/gi, ">").replace(/&lt;/gi, "<").replace(/&amp;/gi, u)
        }
        function Hc(a) {
            a = a.replace(/[\s\xA0]/g, v);
            var c = 0;
            for (var b = 0; b < a.length; ++b) c += a.charCodeAt(b) * 13 * (b + 7);
            return c
        }
        function Ac() {
            var j = [],
            b = [],
            a = 0,
            f = h.getStringByteCount(S[0]);
            if (yb.length > 0 && !eb) {
                eb = i;
                var k = yb.shift();
                B = k.txt;
                a = k.length;
                nc = k.dom;
                var e = B[0],
                d = Math.floor(e.length * fb / a);
                B = [e.substr(0, d), e];
                while (h.getStringByteCount(B[0]) > fb && d > n) {
                    d = Math.floor(d / 2);
                    B = [e.substr(0, d), e]
                }
                cb = {
                    aTxt: [],
                    aSrcSnt: [],
                    aTgtSnt: []
                };
                Db = new Date;
                Gb = B[0].length;
                qb = 1;
                mb = c.serviceClient.TranslateArray(Jb, [B[0]], L, P, c.category ? {
                    Category: c.category
                }: g, mc, zb, lb);
                window.Microsoft.Translator.translationCallsTime[window.Microsoft.Translator.APIRequests] = (new Date).getTime();
                if (window.Microsoft.Translator.APIRequests == 0) Bb = new Date;
                window.Microsoft.Translator.APIRequests++;
                return
            }
            do {
                if (S.length == 0) break;
                if (jb.length && jb[0].length < 32 && S[0].length > 32) jb = [];
                jb.push(S[0].replace(/<[^>]*>/g, hb).replace(/[\s\xA0]+/g, hb));
                Fb -= f;
                a += f;
                lc += S[0].length;
                j.push(Ub.shift());
                b.push(S.shift());
                f = S.length > 0 ? h.getStringByteCount(S[0]) : 0
            } while ( Ub . length > 0 && a < fb && a + f + ( b . length + 1 ) * h.getStringByteCount('"",') <= sc);
            if (a > sc && (!I || !m.sourceFrame)) {
                yb.push({
                    dom: j,
                    txt: b,
                    length: a
                });
                C()
            } else if (a > 0 && (!I || !m.sourceFrame)) {
                Db = new Date;
                Gb = a;
                qb = b.length;
                Pb[F] = function(a) {
                    return function(b) {
                        Mc(b, a)
                    }
                } (F);
                Rb[F] = function(a) {
                    return function(b) {
                        zb(b, a)
                    }
                } (F);
                s[F] = {
                    Dom: j,
                    Txt: b
                };
                s.size++;
                if (c.service == Kb) mb = c.serviceClient.TranslateArray2(Jb, b, L, P, c.category ? {
                    Category: c.category
                }: g, Pb[F], Rb[F], lb);
                else mb = c.serviceClient.TranslateArray(Jb, b, L, P, c.category ? {
                    Category: c.category
                }: g, Pb[F], Rb[F], lb);
                window.Microsoft.Translator.translationCallsTime[F] = (new Date).getTime();
                F++;
                if (window.Microsoft.Translator.APIRequests == 0) Bb = new Date;
                window.Microsoft.Translator.APIRequests++;
                Jc();
                if (s.size < G) if (J && Q) C();
                else setTimeout(function() {
                    C()
                },
                Y)
            } else if (s.size < G) if (J && Q) C();
            else setTimeout(function() {
                C()
            },
            Y)
        }
        function mc(a) {
            if (Hb) return;
            if (!eb) return;
            eb = r;
            window.Microsoft.Translator.APIResponses++;
            m.detectedLanguage = a && a[0] && a[0].From ? a[0].From.toLowerCase() : m.detectedLanguage;
            var p = a[0].TranslatedText,
            f = a[0].OriginalTextSentenceLengths,
            j = a[0].TranslatedTextSentenceLengths,
            l = 0,
            o = 0;
            if (! (p && f && j)) {
                eb = i;
                zb(a[0].Error);
                return
            }
            Nb(a);
            for (var d = 0; d < (B.length > 1 ? Math.max(f.length - 2, 1) : f.length); ++d) {
                cb.aTxt.push(p.substr(o, j[d]));
                cb.aSrcSnt.push(f[d]);
                cb.aTgtSnt.push(j[d]);
                l += f[d];
                o += j[d]
            }
            if (B.length > 1) if (f.length < 1) C();
            else {
                var b = B[1].substr(l),
                q = h.getStringByteCount(b),
                e = Math.floor(b.length * fb / q);
                B = e > fb ? [b.substr(0, e), b] : [b];
                while (h.getStringByteCount(B[0]) > fb && e > n) {
                    e = Math.floor(e / 2);
                    B = [b.substr(0, e), b]
                }
                if (eb) return;
                eb = i;
                Db = new Date;
                Gb = B[0].length;
                qb = 1;
                mb = c.serviceClient.TranslateArray(Jb, [B[0]], L, P, g, mc, zb, lb)
            } else {
                var k = nc.shift(),
                u = rb || m.detectedLanguage;
                if (!Q && !J) {
                    bb = Ab(ob);
                    Vb(k)
                }
                try {
                    kc(k, W(k, K), cb.aTxt.join(v), cb.aSrcSnt, cb.aTgtSnt)
                } catch(t) {
                    if (c.debug);
                }
                if (s.size < G) if (J && Q) C();
                else setTimeout(function() {
                    C()
                },
                Y)
            }
        }
        function Mc(b, a) {
            if (Hb) return;
            window.Microsoft.Translator.translationCallsTime[a] = (new Date).getTime() - window.Microsoft.Translator.translationCallsTime[a];
            var i = (new Date).getTime() - Bb.getTime();
            Bb = new Date;
            window.Microsoft.Translator.totalTranslationTime += i;
            window.Microsoft.Translator.APIResponses++;
            if (s[a] && b.length != s[a].Dom.length) {
                zb("Inconsistent Data", a);
                return
            }
            Nb(b);
            m.detectedLanguage = b && b[0] && b[0].From ? b[0].From.toLowerCase() : m.detectedLanguage;
            var j = rb || m.detectedLanguage;
            if (!Q && !J) {
                bb = Ab(ob);
                Vb(s[a].Dom)
            }
            var e = v;
            for (var f = s[a].Dom.shift(), g = s[a].Txt.shift(), d = b.shift(); f && d; f = s[a].Dom.shift(), (g = s[a].Txt.shift(), d = b.shift())) {
                if (d.Alignment) {
                    if (e.length != 0) e += "|";
                    e += d.Alignment
                }
                try {
                    kc(f, g, d.TranslatedText, d.OriginalTextSentenceLengths, d.TranslatedTextSentenceLengths)
                } catch(h) {
                    if (c.debug);
                }
            }
            if (e.length != 0) nb.setAttribute("mstAlign", e);
            delete s[a];
            s.size--;
            if (s.size < G) if (J && Q) C();
            else setTimeout(function() {
                C()
            },
            Y)
        }
        function Jc() {
            if (F > gc) {
                G = 1;
                Y = 500
            } else if (G > 2 && F % hc == 0) {
                G = G - parseInt(G / 3);
                Y += 10;
                Ob(.1)
            }
        }
        function zb(b, a) {
            if (a) {
                delete s[a];
                s.size--
            }
            if (Hb) return;
            if (c.debug);
            window.Microsoft.Translator.APIResponses++;
            Nb(g);
            if (vc) vc(b);
            if (s.size < G) C()
        }
        function Nb(a) {
            var e = new Date,
            b = e.getTime() - Db.getTime();
            if (b > 13000) b = 13000;
            var c = 0;
            if (a) for (var d = 0; d < a.length; ++d) c += a[d].OriginalTextSentenceLengths.length;
            else c = qb;
            z.push({
                r: a ? "S": "E",
                c: Gb,
                s: c,
                e: qb,
                l: b
            })
        }
        var kc = y.translateElement = function(a, n, l, j, k) {
            a._mstSrcHtml = a.innerHTML;
            if (a.nodeName.toLowerCase() == "option") {
                Z(a, l, K, g, g);
                return
            }
            var d, h = a._mstHash;
            if (m.sourceFrame) d = a.cloneNode(i);
            else {
                d = a;
                a = d.cloneNode(i)
            }
            var o = j ? j.slice(0) : [],
            p = k ? k.slice(0) : [],
            e = [[]],
            f = [[]];
            try {
                Z(a, n, K, o, e);
                Z(d, l, K, p, f)
            } catch(q) {
                if (c.debug);
            }
            if (e.length > 2 && f.length > 2) {
                a._mstSrcHtml = a.innerHTML;
                for (var b = 0; b < e.length && b < f.length; ++b) uc(Yb(e[b]), Yb(f[b]), h * (b + 1))
            } else uc(a, d, h);
            if (I && I[h] && !m.sourceFrame) I.translateElement(I[h], n, l, j, k)
        };
        function uc(a, b, d) {
            if (! (a && b)) return;
            var f = a.textContent || a.innerText || v,
            g = b.textContent || b.innerText || v;
            if (!f.match(/[a-zA-Z0-9\xC0-\uFFFF]/) && !g.match(/[a-zA-Z0-9\xC0-\uFFFF]/)) return;
            a._mstHash = b._mstHash = d;
            if (Lb) b._mstSrcHtml = a.innerHTML;
            try {
                a.setAttribute(M, L);
                b.setAttribute(M, P)
            } catch(e) {
                if (c.debug);
            }
            a._mstNormalize = function() {
                return W(a, K)
            };
            b._mstNormalize = function() {
                return W(b, K)
            };
            b._mstDenormalize = function(d) {
                var b = a.cloneNode(i);
                b._mstNormalize = function() {
                    return W(b, K)
                };
                try {
                    Z(b, d, K)
                } catch(e) {
                    if (c.debug);
                }
                return b
            };
            try {
                if (m.sourceFrame) {
                    m[d] = a;
                    new Cb(a, b, h.getBlockParent(a), Ab(P), m, I)
                } else {
                    m[d] = b;
                    new Cb(b, a, h.getBlockParent(b), Ab(L || m.detectedLanguage), m, I)
                }
            } catch(j) {}
            m.transItems.push({
                src: a,
                tgt: b
            })
        }
        function Tb(a) {
            if (!Lb) throw new Error("Untranslate feature was turned off, please consider modifying the parameter in the constructor!");
            if (a.nodeName.toLowerCase() == "frame" || a.nodeName.toLowerCase() == O) try {
                Tb(a.contentWindow.document.documentElement)
            } catch(b) {
                if (c.debug);
            } else {
                if (a._mstStyle) for (var e in a._mstStyle) try {
                    a.style[e] = a._mstStyle[e]
                } catch(b) {
                    if (c.debug);
                }
                a._mstStyle = g;
                if (a._mstSrcHtml) {
                    a.innerHTML = a._mstSrcHtml;
                    if (a._mstTooltip) a._mstTooltip.detach()
                } else for (var d = 0; d < a.childNodes.length; ++d) try {
                    Tb(a.childNodes[d])
                } catch(b) {
                    if (c.debug);
                }
            }
        }
        if (J && Q) {
            C();
            if (toolbar) toolbar.show()
        } else {
            setTimeout(C, 0);
            if (toolbar) setTimeout(toolbar.show, 10)
        }
        return y
    }
    var Bb = function() {
        function a(b, a) {
            this.Name = b;
            this.Code = a
        }
        return a
    } (),
    Hb = function() {
        function a(b, a, c) {
            this.SignIn = b;
            this.SignOut = a;
            this.Help = c
        }
        return a
    } (),
    Gb = function() {
        var m = "object",
        i = H,
        o = "onComplete",
        f = K,
        h = S,
        c = k,
        d = l,
        n = xb,
        g = j;
        function a() {
            var c = "UserName",
            a = this;
            a.languageNames = [];
            a.langLocalized = g;
            a.appId = window._mstConfig.appId;
            a.unTranslateDelegate = g;
            a.Links = new Hb(window._mstConfig["SignIn"] ? window._mstConfig["SignIn"] : b, window._mstConfig["SignOut"] ? window._mstConfig["SignOut"] : b, "https://go.microsoft.com/?linkid=9722454");
            a.UserName = window._mstConfig[c] ? window._mstConfig[c] : b;
            a.languageCodes = [];
            for (var d in window[n]) a.languageCodes[a.languageCodes.length] = d
        }
        a.prototype.Translate = function(g, h, c, e, b, a, f) {
            this.TranslateElement(g, h, document.documentElement, c, e, b, a, f, d)
        };
        a.prototype.TranslateElement = function(t, m, j, q, e, p, n, l, b) {
            var r = A,
            a = this;
            if (typeof j === r) j = document.documentElement;
            if (typeof b === r) b = d;
            a.validate(t, "from", c, h);
            a.validate(m, "to", d, h);
            if (!a.isElement(j) && !a.isNode(j)) throw new Error("Invalid DomElement");
            a.validate(q, "onProgress", c, f);
            a.validate(e, "onError", c, f);
            a.validate(p, o, c, f);
            a.validate(n, "onRestoreOriginal", c, f);
            a.validate(l, "timeOut", c, i, d);
            a.validate(b, "showFloater", c, "boolean");
            var k = c;
            a.lastToLanguage = m;
            if (a.domTranslator != g && a.domTranslator.cancel) a.domTranslator.cancel();
            if (b) G.Show(m);
            var v = function() {
                s(w);
                k = d;
                try {
                    if (b) G.TranslationComplete()
                } catch(a) {
                    /*console.error(a)*/
                }
                try {
                    if (p) p()
                } catch(a) {
                    console.error(a)
                }
            },
            x = function(c) {
                try {
                    if (b) G.TranslationError(c)
                } catch(a) {
                    console.error(a)
                }
                try {
                    if (e) e(c)
                } catch(a) {
                    console.error(a)
                }
            },
            s = function(c) {
                if (k) return;
                if (c == w) k = d;
                try {
                    if (b) G.TranslationProgress(c)
                } catch(a) {
                    /*console.error(a)*/
                }
                try {
                    if (q) q(c)
                } catch(a) {
                    console.error(a)
                }
            };
            a.domTranslator = new Eb(a.appId, j, t, m, v, e, l, c, c);
            if (a.domTranslator.addProgressEvent) a.domTranslator.addProgressEvent(s);
            if (n) a.unTranslateDelegate = n;
            if (e && l) {
                var u = a.domTranslator;
                setTimeout(function() {
                    if (!k) {
                        e("Timout expired before translation could be finished");
                        if (u.cancel) u.cancel()
                    }
                },
                l)
            }
        };
        a.prototype.validate = function(a, c, f, b, e) {
            var d = " should be of type ";
            if (f) {
                if (!a) throw new Error(c + " is required");
                if (typeof a != b) throw new Error(c + d + b);
            } else if (a && typeof a != b) throw new Error(c + d + b);
            if (b == i && e && a && a < 0) throw new Error(c + " should be a positive number");
        };
        a.prototype.isNode = function(a) {
            return typeof Node === m ? a instanceof Node: a && typeof a === m && typeof a.nodeType === i && typeof a.nodeName === h
        };
        a.prototype.isElement = function(a) {
            return typeof HTMLElement === m ? a instanceof HTMLElement: a && typeof a === m && a !== g && a.nodeType === 1 && typeof a.nodeName === h
        };
        a.prototype.RestoreOriginal = function() {
            var a = this;
            if (!a.domTranslator) throw new Error("Can not RestoreOriginal before making a translation");
            if (a.domTranslator.cancel) a.domTranslator.cancel();
            if (a.unTranslateDelegate) try {
                a.unTranslateDelegate(a.lastToLanguage)
            } catch(b) {
                console.error(b)
            }
        };
        a.prototype.GetLanguagesForTranslate = function(b, e, j, k) {
            var a = this;
            a.validate(b, "locale", d, h);
            a.validate(e, o, d, f);
            a.validate(j, "onError", c, f);
            a.validate(k, "timeOut", c, i, d);
            if (a.languageNames[b] != g) {
                try {
                    e(a.languageNames[b])
                } catch(l) {
                    console.error(l)
                }
                return
            }
            Microsoft.Translator.GetLanguageNames(a.appId, b, a.languageCodes,
            function(a) {
                Microsoft.Translator.Widget.GetLanguageNamesCallBack(a, b, e, j)
            },
            j, k)
        };
        a.prototype.GetLanguageNamesCallBack = function(b, e, g, d) {
            if (!b || !b[0]) {
                if (d) d("Invalid locale " + e);
                return
            }
            var c = [];
            for (var a = 0; a < b.length; a++) c[a] = new Bb(b[a], this.languageCodes[a]);
            this.languageNames[e] = c;
            try {
                g(c)
            } catch(f) {
                console.error(f)
            }
        };
        a.prototype.GetLanguagesForTranslateLocalized = function() {
            var a = this;
            if (!a.langLocalized) {
                a.langLocalized = [];
                for (var b in window[n]) a.langLocalized[a.langLocalized.length] = new Bb(window[n][b], b)
            }
            return a.langLocalized
        };
        a.prototype.GetAutoDetectedLanguage = function() {
            if (!this.domTranslator || !this.domTranslator.detectedLanguage) throw new Error("Can not return auto detected language before making a translation with 'from' parameter set to null ");
            return this.domTranslator.detectedLanguage
        };
        a.prototype.SetUpdatedAccessToken = function(c) {
            var i = z,
            k = window._mstConfig.baseURL.split(s),
            a = k[2].split(":")[0];
            a = a.substring(4, a.length);
            c = encodeURIComponent(c);
            var d = document.getElementsByTagName(O);
            if (d && d.length > 0) for (var h = 0; h < d.length; h++) {
                var l = d[h],
                g = l.getAttribute("src");
                if (g && (g.toLowerCase().indexOf("roledashboard.aspx") != e || g.toLowerCase().indexOf("bulkdashboard.aspx") != e)) {
                    var j = "https://" + k[2],
                    j = j.replace("www.", "ssl.");
                    l.contentWindow.postMessage("TRNS_TOKEN_UPDATE" + c, j)
                }
            }
            var f = document.createElement("script");
            f.type = eb;
            f.charset = "UTF-8";
            var m = window[i].onErrorHandlerName ? hb + window[i].onErrorHandlerName: b;
            f.src = (location && location.href && location.href.indexOf("https") == 0 ? "https://ssl." + a: "https://www." + a) + "/ajax/v3/WidgetV3.ashx?action=refreshToken&trnsaccesstoken=" + c + m;
            p = document.getElementsByTagName(N)[0] || document.documentElement;
            p.insertBefore(f, p.firstChild)
        };
        a.prototype.UpdateAppID = function(a, b) {
            this.appId = a;
            window._mstConfig.appId = this.appId;
            Microsoft.Translator.Widget.UpdateDashboardLink(encodeURIComponent(b))
        };
        a.prototype.UpdateDashboardLink = function(a) {
            Microsoft.Translator.Community.updateWidgetInfo(a)
        };
        return a
    } ();
    q.Widget = new Gb;
    var Cb = new
    function() {
        var e = k,
        g = e,
        m = 600,
        o = 430,
        q = "",
        p = "",
        s;
        return function(s, K, u, Q, z, w) {
            var X = "touchstart",
            W = "pointerup",
            V = "mouseout",
            U = "mouseover",
            G = L,
            A = l,
            v = this;
            if (s._mstTooltip) try {
                s._mstTooltip.detach()
            } catch(gb) {}
            s._mstTooltip = K._mstTooltip = v;
            if (!u) u = s;
            var y = e,
            H = e,
            O = s.style.color,
            M = s.style.backgroundColor,
            k = s.ownerDocument,
            N = e,
            S = v.hover = function(d) {
                if (g) return;
                if (z.showHighlight) {
                    var b = s.style.color;
                    try {
                        b = "#" + C.parse(s.style.color).toString()
                    } catch(c) {}
                    if (b != q) O = s.style.color;
                    var a = s.style.backgroundColor;
                    try {
                        a = "#" + C.parse(s.style.backgroundColor).toString()
                    } catch(c) {}
                    if (a != p) M = s.style.backgroundColor;
                    s.style.color = q;
                    s.style.backgroundColor = p
                }
                if (z.showTooltips && d) {
                    y = A;
                    setTimeout(Z, m)
                }
                if (d && w && w[s._mstHash] && w[s._mstHash]._mstTooltip) w[s._mstHash]._mstTooltip.hover()
            },
            fb = v.unhover = function(a) {
                if (g || N) {
                    N = e;
                    return
                }
                if (z.showHighlight) {
                    s.style.color = O;
                    s.style.backgroundColor = M
                }
                if (z.showTooltips && a) {
                    y = e;
                    setTimeout(Y, m)
                }
                if (a && w && w[s._mstHash] && w[s._mstHash]._mstTooltip) w[s._mstHash]._mstTooltip.unhover()
            };
            function Z() {
                if (g) return;
                if (y) mb()
            }
            var mb = v.show = function() {
                var gb = ab,
                W = a,
                N = "none 0px",
                fb = ib,
                eb = R,
                M = "normal",
                kb = "0px 0px 0px 0px",
                L = B,
                C = t;
                if (H) return;
                else H = A;
                if (!k._mstTooltip) {
                    var nb = c.baseURL.substr(0, 8) + c.baseURL.substr(8).replace(/\/.*$/, jb),
                    X = e;
                    if (db[c.locale] && vb[db[c.locale]] && vb[db[c.locale]] == C) X = A;
                    k._mstTooltip = k.createElement(L);
                    k._mstTooltip.translate = e;
                    k._mstTooltip.setAttribute("translate", I);
                    k._mstTooltip.style.display = G;
                    k._mstTooltip.style.position = bb;
                    k._mstTooltip.style.zIndex = cb;
                    k._mstTooltip.style.margin = kb;
                    k._mstTooltip.style.border = "2px solid #D2D2D2";
                    k._mstTooltip.style.padding = kb;
                    k._mstTooltip.style.color = "#000000";
                    k._mstTooltip.style.backgroundColor = "#E6E6E6";
                    k._mstTooltip.style.fontFamily = "Arial, Helvetica, Sans-Serif";
                    k._mstTooltip.style.fontStyle = M;
                    k._mstTooltip.style.fontVariant = M;
                    k._mstTooltip.style.fontWeight = M;
                    k._mstTooltip.style.fontSize = "12px";
                    k._mstTooltip.style.lineHeight = M;
                    if (!X) {
                        k._mstTooltip.style.direction = d;
                        k._mstTooltip.style.textAlign = D
                    } else {
                        k._mstTooltip.style.direction = C;
                        k._mstTooltip.style.textAlign = eb
                    }
                    var p = k.createElement(L);
                    if (!X) p.style.styleFloat = p.style.cssFloat = eb;
                    else p.style.styleFloat = p.style.cssFloat = D;
                    var j = k.createElement("a");
                    j.href = c.lpURL;
                    j.target = "_blank";
                    j.style.display = fb;
                    j.style.padding = "4px";
                    j.style.border = N;
                    j.style.cursor = P;
                    j.style.textDecoration = G;
                    j.style.textAlign = E;
                    var v = k.createElement(x);
                    v.src = c.imagePath + "binglogo_ctf.png";
                    v.style.width = "36px";
                    v.style.height = "14px";
                    v.style.border = N;
                    j.appendChild(v);
                    p.appendChild(j);
                    k._mstTooltip.cl = k.createElement("a");
                    k._mstTooltip.cl.style.padding = "4px 4px 4px 4px";
                    k._mstTooltip.cl.style.border = N;
                    k._mstTooltip.cl.style.textAlign = E;
                    k._mstTooltip.cl.style.textDecoration = G;
                    k._mstTooltip.cl.style.verticalAlign = "top";
                    k._mstTooltip.cl.style.display = fb;
                    k._mstTooltip.cl.style.cursor = P;
                    var w = k.createElement(x);
                    w.src = c.imagePath + "tooltip_close.gif";
                    w.style.border = N;
                    w.style.width = F;
                    w.style.height = F;
                    k._mstTooltip.cl.appendChild(w);
                    p.appendChild(k._mstTooltip.cl);
                    k._mstTooltip.appendChild(p);
                    var l = k.createElement(L);
                    l.style.margin = "4px 4px 8px 4px";
                    l.style.fontWeight = "bold";
                    l.style.fontFamily = "Segoe UI";
                    l.style.fontSize = "9px";
                    l.style.letterSpacing = "0px";
                    l.style.textTransform = "uppercase";
                    l.style.color = "#4D4D4D";
                    if (!z.sourceFrame) {
                        var q = i;
                        try {
                            q = Ab[db[c.locale || W] || W] || q
                        } catch(V) {}
                        q += ":"
                    } else {
                        var q = "Translation";
                        try {
                            q = localizedTranslation[db[c.locale || W] || W] || q
                        } catch(V) {}
                    }
                    l.appendChild(k.createTextNode(q));
                    k._mstTooltip.appendChild(l);
                    k._mstTooltip.cp = k.createElement(L);
                    k._mstTooltip.appendChild(k._mstTooltip.cp);
                    k._mstTooltip.cb = k.createElement("span");
                    k._mstTooltip.cb.style.display = fb;
                    k._mstTooltip.cb.style.margin = "0px 4px 4px 4px";
                    k._mstTooltip.cb.style.fontFamily = "Arial";
                    k._mstTooltip.cb.style.fontSize = "12px";
                    k._mstTooltip.cb.style.color = "black";
                    k._mstTooltip.cp.appendChild(k._mstTooltip.cb);
                    k.body.appendChild(k._mstTooltip)
                }
                k._mstTooltip.cl.onclick = T;
                k._mstTooltip.style.width = b;
                k._mstTooltip.cb.style.whiteSpace = "nowrap";
                k._mstTooltip.cb.innerHTML = b;
                k._mstTooltip.cb.appendChild(k.createTextNode(K.innerText || K.textContent));
                k._mstTooltip.style.display = J;
                for (var hb in Q) try {
                    k._mstTooltip.cp.style[hb] = Q[hb]
                } catch(V) {
                    if (c.debug);
                }
                k._mstTooltip.onmouseover = function() {
                    y = A;
                    S();
                    Z()
                };
                k._mstTooltip.onmouseout = function() {
                    y = e;
                    setTimeout(Y, n)
                };
                var O = Math.max(h.getVisibleWidth(k), n),
                U = window.pageXOffset || k.documentElement.scrollLeft || k.body.scrollLeft,
                mb = Math.max(k.documentElement.scrollWidth, k.body.scrollWidth);
                if (Microsoft.TranslatorOverride && Microsoft.TranslatorOverride.showTooltip) try {
                    Microsoft.TranslatorOverride.showTooltip(K, s, k._mstTooltip);
                    o = 430
                } catch(V) {}
                var m = k._mstTooltip.cb.offsetWidth + 12;
                if (m > u.offsetWidth) m = u.offsetWidth;
                if (m > O - f) m = O - f;
                if (m < o) m = o;
                k._mstTooltip.style.width = m.toString() + r;
                k._mstTooltip.cb.style.whiteSpace = b;
                var g, lb = h.getStyleValue(s, gb) == C || h.getStyleValue(s, "text-align") == eb;
                if (lb) g = h.absXPos(s) + s.offsetWidth - k._mstTooltip.offsetWidth;
                else g = h.absXPos(s);
                if (g + k._mstTooltip.offsetWidth > h.absXPos(u) + u.offsetWidth) g = h.absXPos(u) + u.offsetWidth - k._mstTooltip.offsetWidth;
                if (g < h.absXPos(u) && h.absXPos(u) + m < mb) g = h.absXPos(u);
                if (h.getStyleValue(s, gb) != C) {
                    if (g + k._mstTooltip.offsetWidth > O + U - 8) g = O + U - 8 - k._mstTooltip.offsetWidth;
                    if (g < U + 8) g = U + 8
                }
                k._mstTooltip.style.left = g + r;
                k._mstTooltip.style.top = Math.max(h.absYPos(s) - (k._mstTooltip.offsetHeight + 8), 8) + r
            };
            function Y() {
                if (g) return;
                if (!y) T()
            }
            var T = v.hide = function() {
                eb(e);
                if (!H) return;
                else H = e;
                if (z.showHighlight) {
                    s.style.color = O;
                    s.style.backgroundColor = M
                }
                if (k._mstTooltip) k._mstTooltip.style.display = G
            },
            eb = v.setLock = function(a) {
                g = a
            },
            nb = v.tap = function(a) {
                if (window.WidgetLastHoveredItem != j) window.WidgetLastHoveredItem._mstTooltip.unhover(window.WidgetLastHoveredItem);
                N = A;
                if (k._mstTooltip) setTimeout(function() {
                    k._mstTooltip.style.display = J
                },
                m + 10);
                window.WidgetLastHoveredItem = a
            },
            ob = v.detach = function() {
                h.removeEvent(s, U, kb);
                h.removeEvent(s, V, lb);
                h.removeEvent(s, s, window.navigator.msPointerEnabled ? W: X, hb)
            },
            kb = h.addEvent(s, U, S),
            lb = h.addEvent(s, V, fb),
            hb = h.addEvent(s, window.navigator.msPointerEnabled ? W: X, nb)
        }
    },
    Ib = new
    function(H) {
        var nb = 1600,
        N = x,
        A = "span",
        v = B,
        eb = r,
        wb = j,
        i = L,
        w = J,
        C = ib,
        Lb = "MSTCTransPanel",
        Q = l,
        d = k,
        z = this,
        mc = 0,
        Db, Kb, Eb, zb, Ib, g, Bb, Z, gb, Cb, Xb, y, ub, kb, jb, fb, db, Wb, Y, Mb, ec, sb, Pb, ac, bc, Tb, dc, Ub, ic, lc, Jb, fc, a, G, mb, Zb, Ab, xb, qb, p, jc, lb = d,
        Fb = Q,
        gc = 1e6,
        m, o = 0,
        Gb, E, X;
        window._mstCmCb = function() {
            c.appId = document.getElementById("MSTCAppIdToken").innerHTML;
            mb = parseInt(document.getElementById("MSTCMaxRating").innerHTML);
            Zb = document.getElementById("MSTCImagePath").innerHTML;
            Ab = document.getElementById("MSTCAuthLang").innerHTML.toLowerCase();
            xb = document.getElementById("MSTCDashboardUrl").href;
            Bb = document.getElementById("MSTCContent");
            Z = document.getElementById("MSTCExpandLink");
            gb = document.getElementById("MSTCRootPanel");
            Cb = document.getElementById("MSTCLoading");
            Xb = document.getElementById("MSTCSubmitting");
            y = document.getElementById(Lb);
            ub = document.getElementById("MSTCPrevNextPanel");
            kb = document.getElementById("MSTCPrevLink");
            jb = document.getElementById("MSTCNextLink");
            fb = document.getElementById("MSTCPrevCount");
            db = document.getElementById("MSTCNextCount");
            Wb = document.getElementById("MSTCFooterPanel");
            Y = document.getElementById("MSTCDashboardLink");
            bc = document.getElementById("MSTCApprove");
            Tb = document.getElementById("MSTCApproveTooltip");
            dc = document.getElementById("MSTCReject");
            Ub = document.getElementById("MSTCRejectTooltip");
            Mb = document.getElementById("MSTCTransPanelError");
            ec = document.getElementById("MSTCTransPanelErrorMsg");
            sb = document.getElementById("MSTCOKImgBtn");
            Pb = document.getElementById("MSTCHelpImgBtn");
            if (sb) sb.onclick = ob;
            if (h.isInternetExplorer() && h.isQuirksMode(document)) h.fixIEQuirks(g);
            kb.onclick = function() {
                vb( - 3);
                return d
            };
            jb.onclick = function() {
                vb(3);
                return d
            };
            if (Y) if (Fb) {
                Y.onclick = Hb;
                var a = document.getElementById("MSTTDashboardLink");
                if (a) {
                    a.parentNode.style.display = C;
                    a.onclick = Hb;
                    a.href = "javascript: " + a.title
                }
            } else Y.style.visibility = W;
            if (!window.Microsoft) window.Microsoft = {};
            window.Microsoft.TranslatorOverride = {
                showTooltip: Yb,
                hideTooltip: yb
            };
            if (mb >= 5) window.Microsoft.TranslatorOverride.showHighlight = Vb
        };
        var Yb = z.showTooltip = function(a, b, c) {
            if (!g || g.ownerDocument != c.ownerDocument) return;
            p = a._mstTooltip;
            Kb = a.getAttribute(M);
            Eb = b.getAttribute(M);
            zb = a;
            Ib = b;
            lb = d;
            Z.onclick = Ob;
            g.style.display = w;
            gb.style.display = i;
            y.style.display = i;
            ob();
            c.appendChild(g)
        },
        Ob = z.showTranslations = function() {
            Z.onclick = Nb;
            gb.style.display = w;
            Cb.style.display = w;
            y.style.display = i;
            ob();
            ub.style.display = i;
            c.serviceClient.GetTranslations(c.appId, zb._mstNormalize(), Kb, Eb, 24, c.category ? {
                Category: c.category
            }: wb, Qb, Rb, 7e3);
            return d
        },
        kc = z.updateWidgetInfo = function(a) {
            G = a;
            qb = Q
        };
        function Qb(a) {
            Cb.style.display = i;
            y.innerHTML = b;
            y.style.display = w;
            if (a.Translations.length > 4) ub.style.display = w;
            var u = mb >= 5 && mb >= Math.abs(a.Translations[0].Rating) && (!Ab || Ab == Eb.toLowerCase()),
            n = a.Translations.length > 0 && a.Translations[0].Rating >= 5,
            v = !a.NoEdit && a.Translations.length == 1,
            s = a.Reject,
            h,
            p = a.Translations.length;
            for (h = 0; h < p; h++) if (a.Translations[h].Rating == 5) break;
            if (h != p) {
                var q = a.Translations[h].TranslatedText;
                for (var f = 0; f < a.Translations.length; f++) {
                    if (f == h) continue;
                    if (q == a.Translations[f].TranslatedText) {
                        if (f < h) h--;
                        a.Translations.splice(f, 1);
                        f--
                    }
                }
            }
            var j = e,
            l = e;
            for (var f = 0; f < a.Translations.length; ++f) {
                if (j == e && a.Translations[f].Rating < 5) j = f;
                if (j != e && a.Translations[f].Rating > -5) l = f
            }
            if (j >= 0 && l > j) for (var f = j; f < l; ++f) for (var k = f + 1; k <= l; ++k) if (a.Translations[f].Count < a.Translations[k].Count) {
                var x = a.Translations[f];
                a.Translations[f] = a.Translations[k];
                a.Translations[k] = x
            }
            m = [];
            while (a.Translations.length > 0) {
                var r = a.Translations.shift();
                try {
                    m.push(new Sb(r, y, u, n, v, s))
                } catch(t) {
                    if (c.debug);
                    continue
                }
                if (n) n = d
            }
            if (a.Hover && m.length && m[0].hover) m[0].hover();
            o = 0;
            vb();
            if (document._mstTooltip && (document._mstTooltip.style.display == i || g.style.display == i)) yb();
            return m.slice(0)
        }
        function ob() {
            Mb.style.display = i
        }
        function Rb() {
            if (c.debug);
            Nb()
        }
        function Nb() {
            lb = d;
            p.setLock(d);
            Z.onclick = Ob;
            g.style.display = w;
            gb.style.display = i;
            y.style.display = i;
            ob();
            return d
        }
        function vb(c) {
            if (lb) return d;
            if (!c) o = 0;
            else o += c;
            if (o < 0) o = 0;
            else if (o >= m.length) o -= 3;
            p.setLock(Q);
            for (var a = 0; a < m.length; ++a) if (a >= o && a < o + 3) m[a].panel.style.display = w;
            else m[a].panel.style.display = i;
            var f = o,
            e = Math.max(m.length - (o + 3), 0);
            fb.innerHTML = "(" + f.toString() + ")";
            db.innerHTML = "(" + e.toString() + ")";
            if (f > 0) {
                kb.style.color = "#59F";
                fb.style.display = b
            } else {
                kb.style.color = "#999";
                fb.style.display = i
            }
            if (e > 0) {
                jb.style.color = "#59F";
                db.style.display = b
            } else {
                jb.style.color = "#999";
                db.style.display = i
            }
            setTimeout(function() {
                p.setLock(d)
            },
            500)
        }
        function Sb(a, y, B, z) {
            var s = "4px 1px 0px 3px",
            r = "4px 3px 0px 1px",
            q = ab,
            e = this.panel = document.createElement(v);
            e.className = Lb;
            y.appendChild(e);
            a.OriginalText = zb._mstNormalize();
            var u = Ib._mstDenormalize(a.TranslatedText),
            l = document.createElement(v);
            l.className = "MSTCTransBox";
            if (z) l.style.color = "#006622";
            l.appendChild(document.createTextNode(u.innerText || u.textContent));
            e.appendChild(l);
            var b = document.createElement(v);
            b.className = "MSTCStatsTab";
            e.insertBefore(b, e.firstChild);
            var k = document.createElement(v);
            k.className = "MSTCVoteCount";
            b.appendChild(k);
            if (a.Rating > 5) {
                var i = document.createElement(A),
                w = document.createElement(N);
                w.src = c.imagePath + "ctfbadge.gif";
                i.appendChild(w);
                k.appendChild(i);
                if (a.Rating >= 10) i.style.backgroundColor = "#F2C341";
                else if (a.Rating >= 8) i.style.backgroundColor = "#B2B2B2";
                else if (a.Rating >= 6) i.style.backgroundColor = "#8C7853"
            } else if (a.Rating == 5) {
                var x = document.createElement(A),
                d = document.createElement(N);
                d.src = c.imagePath + "ctfmt.gif";
                d.style.margin = "2px 2px 0px 3px";
                x.appendChild(d);
                k.appendChild(x)
            } else if (a.Count) {
                var p = document.createElement(A),
                g = document.createElement(V);
                g.style.display = C;
                g.appendChild(document.createTextNode(a.Count));
                p.appendChild(g);
                var d = document.createElement(N);
                d.src = c.imagePath + "ctfvotes.gif";
                p.appendChild(d);
                k.appendChild(p);
                if (h.getStyleValue(Bb, q) == t) {
                    g.style.margin = r;
                    d.style.margin = "7px 1px 0px 3px"
                } else {
                    g.style.margin = s;
                    d.style.margin = "3px 3px 0px 1px"
                }
            } else b.parentNode.removeChild(b);
            if (a.Flags) {
                var j = document.createElement(v);
                j.className = "MSTCFlagCount";
                j.style.marginTop = "2px";
                b.appendChild(j);
                var n = document.createElement(A);
                n.style.width = n.style.minWidth = "1px";
                n.style.height = "21px";
                j.appendChild(n);
                var o = document.createElement(A),
                f = document.createElement(V);
                f.style.display = C;
                f.appendChild(document.createTextNode(a.Flags));
                o.appendChild(f);
                var m = document.createElement(N);
                m.src = c.imagePath + "ctfflags.gif";
                o.appendChild(m);
                j.appendChild(o);
                if (h.getStyleValue(Bb, q) == t) {
                    f.style.margin = r;
                    m.style.margin = "7px 1px 0px 2px"
                } else {
                    f.style.margin = s;
                    m.style.margin = "7px 2px 0px 1px"
                }
            }
            b.style.marginTop = (e.offsetHeight - b.offsetHeight) / 2 + eb
        }
        var yb = z.hideTooltip = function() {
            lb = d;
            g.style.display = i;
            if (p) p.hide();
            if (a && a.parentNode == document.body) try {
                document.body.removeChild(a)
            } catch(b) {}
        },
        Hb = z.showDashboard = function() {
            var j = bb;
            yb();
            if (p) p.setLock(Q);
            if (!xb) return;
            a = document.createElement(v);
            a.style.position = j;
            a.style.zIndex = cb;
            a.style.width = "97%";
            a.style.margin = "44px 8px";
            a.style.borderRight = a.style.borderBottom = "solid 1px black";
            a.style.backgroundColor = "white";
            var i = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight || n;
            if (i < n) i = n;
            i -= 60;
            var g = document.createElement(O);
            g.style.width = "100%";
            g.style.height = i.toString() + eb;
            g.src = 'javascript:document.write("Loading...")';
            a.appendChild(g);
            var b = document.createElement("a");
            try {
                h.applyProtectiveCss(b)
            } catch(k) {
                if (c.debug);
            }
            b.style.display = C;
            b.style.position = j;
            b.style.styleFloat = R;
            b.style.top = "4px";
            b.style.cursor = P;
            b.title = "Close dashboard";
            var f = document.createElement(A);
            f.style.display = C;
            f.style.width = "28px";
            f.style.height = "28px";
            f.style.marginRight = "16px";
            b.appendChild(f);
            var e = document.createElement(N);
            try {
                h.applyProtectiveCss(e)
            } catch(k) {
                if (c.debug);
            }
            e.src = c.imagePath + "ctfdashboardclose.gif";
            e.style.display = C;
            e.style.marginTop = F;
            e.style.marginLeft = F;
            e.style.border = "0px";
            f.appendChild(e);
            b.onclick = function() {
                if (p) p.setLock(d);
                document.body.removeChild(a)
            };
            a.appendChild(b);
            a.style.height = i.toString() + eb;
            a.style.overflow = W;
            a.style.textAlign = D;
            window.scrollTo(0, 0);
            document.body.insertBefore(a, document.body.firstChild);
            setTimeout(function() {
                b.style.right = "4px";
                if (!h.isInternetExplorer()) b.style.left = (a.offsetWidth - b.offsetWidth).toString() + eb;
                var d = encodeURIComponent(location.href);
                if (d.lenght > nb) d = d.substr(0, nb);
                var e = xb + "?" + rb() + "&url=" + d + "&from=" + encodeURIComponent(c.from) + "&to=" + encodeURIComponent(c.to) + "&category=" + encodeURIComponent(c.category) + "&usr=" + encodeURIComponent(cc());
                g.src = e
            },
            0);
            return d
        };
        function rb() {
            var a = b;
            if (!qb) a += "siteData=" + G;
            else a += "trnsaccesstoken=" + G;
            if (E) a += hb + E;
            return a
        }
        var q = 0;
        function Vb(a, b, c) {
            if (!a.transItems || !a.transItems.length) return;
            q = 0;
            tb(a, b, c)
        }
        function tb(a, f, g) {
            if (q >= a.transItems.length) return;
            var b = [],
            e = 0;
            for (var d = q; d < a.transItems.length && e < nb && d - q < 10; ++d) {
                var i = a.transItems[d].src._mstNormalize();
                e += h.getStringByteCount(i);
                b.push(i)
            }
            if (e >= nb) b.pop();
            c.serviceClient.GetTranslationsArray(c.appId, b, f, g, 3, c.category ? {
                Category: c.category
            }: wb,
            function(d) {
                for (var c = 0; c < d.length; ++c) if (d[c].Translations.length > 1) if (d[c].Translations[0].Rating > 5) a.transItems[q + c].tgt.style.backgroundColor = "#E6E6E6";
                else if (d[c].Translations[1].Count < 0) a.transItems[q + c].tgt.style.backgroundColor = "#E5917F";
                else a.transItems[q + c].tgt.style.backgroundColor = "#B9E4FC";
                q += b.length;
                tb(a, f, g)
            },
            function() {
                q += b.length > 1 ? b.length: 1;
                tb(a, f, g)
            },
            7e3)
        }
        var hc = z.forceLoad = function() {
            if (X) X()
        };
        function cc() {
            var a = Jb.innerText || Jb.textContent;
            if (!a) {
                var b = document.cookie.match(/mstcid=([^;]+)/i);
                if (b) a = b[1];
                else {
                    a = Math.floor(Math.random() * 1e9).toString(f);
                    document.cookie = "mstcid=" + a + "; expires=Sun, 01-Jan-2040 01:01:01 GMT; path=" + ((location.host.indexOf("bing") > e && location.pathname.indexOf("/translator")) > e ? location.pathname: s)
                }
            }
            return a
        }
        new
        function() {
            var a, e;
            a = H.match(/siteData=([^&]*)/i);
            if (a) {
                G = a[1];
                qb = d
            } else {
                a = H.match(/trnsaccesstoken=([^&]*)/i);
                if (a) {
                    G = a[1];
                    qb = Q
                }
            }
            a = H.match(/onerror=([^&]+)/i);
            if (a) E = a[1];
            Db = c.locale;
            a = H.match(/loc=([^&]+)/);
            if (a) Db = a[1];
            a = H.match(/ctfLanguages=([^&]*)/);
            if (a) e = a[1];
            a = H.match(/showDashboard=([^&]*)/);
            if (a && (a[1].toLowerCase() == U || a[1].toLowerCase() == I)) Fb = d;
            if (e) {
                Gb = {};
                var j = e.split(",");
                for (var f = 0; f < j.length; ++f) Gb[j[f].toLowerCase()] = 1
            }
            if (G) X = function() {
                var a = "MicrosoftTranslatorCommunity";
                if (!X) return;
                X = wb;
                g = document.getElementById(a);
                if (g) g.parentNode.removeChild(g);
                g = document.createElement(v);
                g.id = a;
                g.style.display = i;
                document.body.insertBefore(g, document.body.firstChild);
                var d = b;
                if (h.isInternetExplorer() && h.isQuirksMode(document)) d = "&inrt=1";
                ac = pb("/ajax/v3/community.aspx?fmt=js&loc=" + Db + d + u + rb(), c.rootURL)
            };
            if (c.tokRef) {
                window._mstRefTok = function(a) {
                    c.appId = a
                };
                setInterval(function() {
                    if (_eTokenScript) _eTokenScript.parentNode.removeChild(_eTokenScript);
                    _eTokenScript = pb("/ajax/v3/community.aspx?reftok=1&" + rb(), c.rootURL)
                },
                c.tokRef * 1e3)
            }
            function k(a) {
                var b = T;
                if (typeof a.data == S && a.data.indexOf(b) == 0) {
                    var c = a.data.substring(b.length);
                    if (window[E] && typeof window[E] == K) window[E](c)
                }
            }
            if (window.addEventListener) addEventListener("message", k, d);
            else attachEvent("onmessage", k)
        };
        c.serviceClient.Community = z
    } (c.baseURL),
    G; (function(h) {
        var Z = "dragging",
        Y = "__mstto=",
        q = "value",
        B = "{0}",
        v = L,
        V = "style",
        f = "LanguageMenu",
        p = J,
        X = z,
        m = l,
        d = k,
        x = j,
        s = {},
        M, i, o = x,
        t = x,
        D = d,
        F, g, I, R, Q, P, n, S, T, hb = d,
        fb = m,
        C = d,
        E = m,
        eb = d,
        gb = d,
        O;
        function mb(r, h, a) {
            var e = "_bwmid",
            l = "Microsoft.Translator.OnMouseOverFloater()",
            k = "onmouseover",
            j = A;
            if (typeof h === j) h = "true";
            if (typeof a === j) a = b;
            F = Util.GetElement("WidgetFloater");
            g = Util.GetElement(ub);
            I = Util.GetElement("WidgetFloaterCollapsed");
            R = Util.GetElement("FloaterSharePanel");
            Q = Util.GetElement("FloaterEmbed");
            P = Util.GetElement("FloaterProgressBar");
            O = a == b;
            o = a;
            var f = document.createElement("link");
            f.setAttribute("href", window[X].floaterStylePath);
            f.setAttribute("rel", "stylesheet");
            var n = document.getElementsByTagName(N)[0];
            n.insertBefore(f, n.firstChild);
            g.onmousedown = lb;
            F.setAttribute(k, l);
            F.setAttribute("onmouseout", "Microsoft.Translator.OnMouseOutFloater()");
            I.setAttribute(k, l);
            M = r;
            Microsoft.Translator.Widget.GetLanguagesForTranslate(r, bb, ab);
            var p = g.getElementsByTagName("input");
            for (var i = 0; i < p.length; i++) {
                var q = p[i];
                if (q.getAttribute("type").toLowerCase() == "text") q.setAttribute("onclick", "this.select()")
            }
            if (h.toLowerCase() == U) fb = d;
            eb = m;
            if (window[e]) window[e] += ",translator";
            else window[e] = "translator";
            pb("widget/metrics.js", (document.location.protocol == "https:" ? "https://ssl": "https://www") + ".bing.com/");
            if (!hb && fb) {
                c.serviceClient.Community.forceLoad();
                hb = m
            }
        }
        h.Initialize = mb;
        function db() {
            g.style.display = p
        }
        function H(d) {
            if (!eb) {
                setTimeout(function() {
                    H(d)
                },
                50);
                return
            }
            var c;
            if (!gb) if (c = document.getElementById("WidgetLauncher")) {
                var a = c.getBoundingClientRect();
                if (window["Util"].IsElementInViewport(c)) if (a.left == 0 && a.top == 0) setTimeout(function() {
                    a = c.getBoundingClientRect();
                    G(a.left, a.top)
                },
                200);
                else G(a.left, a.top);
                else G(50, 50)
            } else if (!c) G(50, 50);
            gb = m;
            y();
            db();
            F.style.display = p;
            i = d;
            var e = setInterval(function() {
                if (window[f]) {
                    window[f].onChanged = kb;
                    try {
                        try {
                            window[f].setValue(i)
                        } catch(a) {
                            console.error(a)
                        }
                        n = Util.GetElement("OriginalLanguageSpan");
                        if (o == b) n.parentNode[V].display = v;
                        else {
                            n.parentNode[V].display = p;
                            if (O) n.innerHTML = window[X].autoDetected.replace(B, s[o]);
                            else n.innerHTML = s[o]
                        }
                    } catch(a) {
                        console.warn(a)
                    }
                    clearInterval(e)
                }
            },
            1);
            E = m;
            if (t) clearTimeout(t);
            if (!C) {
                D = m;
                K()
            }
        }
        h.Show = H;
        function Bb() {
            g.style.display = v
        }
        function y() {
            F.style.display = v;
            R.style.display = v;
            I.style.display = v;
            Q.style.display = v;
            E = d;
            clearTimeout(t)
        }
        function G(a, b) {
            g.style.top = b + r;
            g.style.left = a + r
        }
        function tb() {
            cb();
            D = m;
            K()
        }
        h.TranslationComplete = tb;
        function vb(g) {
            if (g >= 0 && g < w) {
                D = d;
                clearTimeout(t);
                cb();
                ib()
            }
            var e = x;
            try {
                e = Microsoft.Translator.Widget.GetAutoDetectedLanguage()
            } catch(l) {}
            if (e && window[f] && window[f].getValue) {
                o = e;
                n.parentNode[V].display = p;
                if (O) n.innerHTML = window[X].autoDetected.replace(B, s[o]);
                else n.innerHTML = s[o];
                var k = s[e],
                h = s[window[f].getValue()],
                i = location.href.substr(0, location.href.length - (location.hash || b).length),
                j = document.location.search.length == 0 ? "?": u,
                a = Util.GetElement("EmailSubject").getAttribute(q);
                a = a.replace(B, h);
                a = a.replace("{1}", k);
                var c = Util.GetElement("EmailBody").getAttribute(q);
                c = c.replace(B, encodeURIComponent(i + j + Y + window[f].getValue()));
                c = c.replace("{1}", encodeURIComponent(i));
                Util.GetElement("EmailLink").setAttribute("href", "mailto:?charset=utf-8&subject=" + a + "&body=" + c);
                Util.GetElement("ShareHelpLink").setAttribute("title", Util.GetElement("ShareHelpText").getAttribute(q).replace(B, h));
                window["Util"].SetCookie("mstto", window[f].getValue(), d)
            }
        }
        h.TranslationProgress = vb;
        function Ab(a) {
            console.log(a)
        }
        h.TranslationError = Ab;
        function qb() {
            Microsoft.Translator.Widget.RestoreOriginal();
            Bb()
        }
        h.OnClose = qb;
        function zb() {
            y();
            H(i)
        }
        h.OnShareBackClick = zb;
        function yb() {
            y();
            H(i)
        }
        h.OnEmbedBackClick = yb;
        function wb() {
            clearTimeout(t);
            C = m;
            H(i)
        }
        h.OnMouseOverFloater = wb;
        function xb() {
            C = d;
            if (E) K()
        }
        h.OnMouseOutFloater = xb;
        function K() {
            if (D && !C && E) t = setTimeout(function() {
                sb()
            },
            7e3)
        }
        function jb() {
            var c = "ShareTextbox";
            y();
            db();
            var a = location.href.substr(0, location.href.length - (location.hash || b).length);
            if (location.search.length == 0) Util.GetElement(c).setAttribute(q, a + "?__mstto=" + i);
            else if (location.search.indexOf("__mstto") != e) {
                if (a.match(/__mstto=(.+)([&]+)/i)) Util.GetElement(c).setAttribute(q, a.replace(/__mstto=(.+)([&&]+)/i, Y + i + u));
                else if (a.match(/__mstto=(.+)/i)) Util.GetElement(c).setAttribute(q, a.replace(/__mstto=(.+)/i, Y + i))
            } else Util.GetElement(c).setAttribute(q, a + "&amp;__mstto=" + i);
            R.style.display = p
        }
        h.ShowSharePanel = jb;
        function ob() {
            y();
            Q.style.display = p
        }
        h.ShowEmbed = ob;
        function sb() {
            if (D && !C && E) {
                y();
                I.style.display = p
            }
        }
        function bb(b) {
            for (var a = 0; a < b.length; a++) s[b[a].Code] = b[a].Name
        }
        function ab() {
            if (M != a) {
                M = a;
                Microsoft.Translator.Widget.GetLanguagesForTranslate(a, bb, ab)
            }
        }
        function cb() {
            P.style.visibility = W
        }
        function ib() {
            P.style.visibility = "visible"
        }
        function kb() {
            if (i.toLowerCase() != window[f].getValue().toLowerCase()) {
                clearTimeout(t);
                Microsoft.Translator.Widget.Translate(o, window[f].getValue());
                i = window[f].getValue();
                window[f].elemHeader.focus()
            }
        }
        function lb(a) {
            a = a || event;
            S = a.clientX;
            T = a.clientY;
            document.onmousemove = rb;
            document.onmouseup = nb;
            document.body.focus();
            document.onselectstart = function() {
                return d
            };
            g.ondragstart = function() {
                return d
            };
            Util.addClass(g, Z);
            return d
        }
        function rb(a) {
            a = a || event;
            var b = Util.getPosition(g),
            c = a.clientX - S,
            e = a.clientY - T;
            G(parseInt(b.left) + c, parseInt(b.top) + e);
            S = a.clientX;
            T = a.clientY;
            return d
        }
        function nb(a) {
            a = a || event;
            document.onmousemove = x;
            document.onselectstart = x;
            g.ondragstart = x;
            Util.removeClass(g, Z);
            return d
        }
    })(G || (G = {}));
    q.FloaterInitialize = function(b, a, c) {
        G.Initialize(b, a, c)
    };
    q.FloaterShowSharePanel = function() {
        G.ShowSharePanel()
    };
    q.FloaterShowEmbed = function() {
        G.ShowEmbed()
    };
    q.FloaterOnClose = function() {
        G.OnClose();
        return k
    };
    q.FloaterOnShareBackClick = function() {
        G.OnShareBackClick()
    };
    q.FloaterOnEmbedBackClick = function() {
        G.OnEmbedBackClick()
    };
    q.OnMouseOverFloater = function() {
        G.OnMouseOverFloater();
        return k
    };
    q.OnMouseOutFloater = function() {
        G.OnMouseOutFloater();
        return k
    };
    var yb = document.getElementById(ub);
    if (yb != j) yb.parentNode.removeChild(yb)
};
function CUtil() {
    var f = "character",
    e = true,
    d = false,
    b = null,
    c = -1,
    a = this,
    g = navigator.userAgent.toLowerCase();
    a.MSIE = g.indexOf("msie") != c && g.indexOf("opera") == c;
    a.MSIE6 = a.MSIE && g.indexOf("msie 6.") != c;
    a.MSIE7 = a.MSIE && g.indexOf("msie 7.") != c;
    a.FIREFOX = g.indexOf("firefox") != c;
    a.SAFARI = g.indexOf("applewebkit") != c;
    a.GetPath = function() {
        var a = "/";
        if (location.pathname) {
            a = location.pathname.match(/\/\w*/i);
            if (a) a = a[0]
        }
        return a
    };
    a.AddFavorites = function() {
        var a = document.title,
        b = window.location.href;
        if (this.FIREFOX) window.sidebar.addPanel(a, b, "");
        else window.external.AddFavorite(b, a)
    };
    a.SetCookie = function(c, b, d, a) {
        if (window.mscc === undefined || window.mscc.hasConsent()) {
            if (!a) a = "/";
            document.cookie = c + "=" + b + (d ? "; expires=Sun, 01-Jan-2040 01:01:01 GMT": "") + "; path=" + a
        }
    };
    a.DeleteCookie = function(b, a) {
        if (!a) a = "/";
        document.cookie = b + "=;Thu, 01 Jan 1970 00:00:01 GMT; path=" + a
    };
    a.GetCookie = function(d) {
        var c = "document.cookie.match(/",
        a = eval(c + d + "s*=([^;]*)(;|$)/);");
        if (a != b) return a[1];
        else {
            a = eval(c + d + "s*([^;]*)(;|$)/);");
            if (a != b) return a[1];
            else return b
        }
    };
    a.AddEvent = function(a, b, c) {
        if (a.addEventListener) a.addEventListener(b, c, d);
        else if (a.attachEvent) a.attachEvent("on" + b, c)
    };
    a.RemoveEvent = function(a, b, c) {
        if (a.removeEventListener) a.removeEventListener(b, c, d);
        else if (a.detachEvent) a.detachEvent("on" + b, c)
    };
    a.AbsXPos = function(a) {
        return a.offsetLeft + (a.offsetParent != b ? this.AbsXPos(a.offsetParent) : 0)
    };
    a.AbsYPos = function(a) {
        return a.offsetTop + (a.offsetParent != b ? this.AbsYPos(a.offsetParent) : 0)
    };
    a.SetDDLByVal = function(c, b) {
        for (var a = 0; a < b.options.length; a++) if (b.options[a].value == c) {
            b.options[a].selected = e;
            return
        }
    };
    a.GetElement = function(a) {
        if (arguments.length <= 0) return b;
        if (document.getElementById) return document.getElementById(a);
        else if (document.all) return document.all(a);
        else if (document.layers) return window.document.layers[a];
        else return b
    };
    a.GetStyleObject = function(a) {
        if (document.getElementById && document.getElementById(a)) return document.getElementById(a).style;
        else if (document.all && document.all(a)) return document.all(a).style;
        else if (document.layers && document.layers[a]) return document.layers[a];
        else return d
    };
    a.GetStyleValue = function(e, c) {
        var a = document.getElementById(e) || document.body,
        d;
        if (a.currentStyle) d = a.currentStyle[c] || a.currentStyle.getAttribute(c.replace("-"));
        else if (window.getComputedStyle) d = document.defaultView.getComputedStyle(a, b).getPropertyValue(c);
        return d
    };
    a.GetComputedStyle = function(e, c) {
        var a = document.getElementById(e) || document.body,
        d;
        if (window.getComputedStyle) d = document.defaultView.getComputedStyle(a, b).getPropertyValue(c);
        else if (a.currentStyle) d = a.currentStyle[c] || a.currentStyle.getAttribute(c.replace("-"));
        return d
    };
    a.GetScrollBounds = function(a) {
        if (a == b) return {
            x: 0,
            y: 0,
            width: 0,
            height: 0
        };
        var e, f, d, c;
        if (a.documentElement != b && a.documentElement.scrollTop != b && a.documentElement.scrollTop >= a.body.scrollTop) {
            e = a.documentElement.scrollLeft;
            f = a.documentElement.scrollTop;
            d = a.documentElement.scrollWidth;
            c = a.documentElement.scrollHeight
        } else {
            e = a.body.scrollLeft;
            f = a.body.scrollTop;
            d = a.body.scrollWidth;
            c = a.body.scrollHeight
        }
        return {
            x: e,
            y: f,
            width: d,
            height: c
        }
    };
    a.getLanguageDirStyle = function(b) {
        var a;
        if (Microsoft.Translator.languageDirs[b] == "rtl") a = {
            direction: "rtl",
            textAlign: "right"
        };
        else a = {
            direction: "ltr",
            textAlign: "left"
        };
        return a
    };
    a.setScrollValue = function(a, b, e, f, c) {
        var d = a.ownerDocument.defaultView ? a.ownerDocument.defaultView: a.ownerDocument.parentWindow;
        if (d.scrollBy) d.scrollBy(e, f);
        else {
            a["scroll" + c] = b;
            a.ownerDocument.body["scroll" + c] = b
        }
    };
    a.GetUrlParameter = function(e, a) {
        a = a.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var d = "[\\?&]" + a + "=([^&#]*)",
        f = new RegExp(d, "i"),
        c = f.exec(e);
        if (c == b) return b;
        else return c[1]
    };
    a.GetDocumentUrl = function(d) {
        var e = "/bv.aspx",
        b = "a=",
        a = "";
        if (d.location.hash.length > 1) a = d.location.hash.substring(1);
        else if (d.location.search.indexOf(b) > 0) a = decodeURIComponent(d.location.search.substring(d.location.search.indexOf(b) + 2));
        while (a && a.toLowerCase().indexOf(e) >= 0 && a.toLowerCase().indexOf(b) >= 0) a = decodeURIComponent(a.substring(a.toLowerCase().indexOf(b) + 2));
        if (a.length > 0) {
            a = a.replace(/^\s*/, "").replace(/\s*$/, "");
            if (a.indexOf("?") == c) a = a.replace("&", "?")
        }
        if (a && a.indexOf("://") == c) a = "https://" + a;
        if (a && a.toLowerCase().indexOf(e) >= 0) a = "";
        return a
    };
    a.SendPostRequest = function(f, c, e) {
        var a = document.createElement("form");
        a.action = f;
        a.method = "post";
        a.target = e;
        for (var d in c) if (c.hasOwnProperty(d)) {
            var b = document.createElement("input");
            b.name = d;
            b.value = c[d];
            b.type = "hidden";
            a.appendChild(b)
        }
        document.body.appendChild(a);
        a.submit();
        document.body.removeChild(a)
    };
    a.Log = function(b, a) {
        if (window.mscc === undefined || window.mscc.hasConsent()) Microsoft.Translator.LoadScript("/sync.ashx?svc=" + b + "&" + a.join("&"))
    };
    a.GetCaretPosition = function(a) {
        var g = 0;
        if (a.selectionStart || a.selectionStart == "0") g = a.selectionStart;
        else if (document.selection) {
            var i = document.selection.createRange(),
            j = 0,
            k = 0;
            if (i && i.parentElement() == a) {
                var e = a.value.length,
                l = a.value.replace(/\r\n/g, "\n"),
                b = a.createTextRange();
                b.moveToBookmark(i.getBookmark());
                var h = a.createTextRange();
                h.collapse(d);
                if (b.compareEndPoints("StartToEnd", h) > c) j = k = e;
                else {
                    j = -b.moveStart(f, -e);
                    if (b.compareEndPoints("EndToEnd", h) > c) k = e;
                    else k = -b.moveEnd(f, -e)
                }
            }
            g = j
        }
        return g
    };
    a.SetSelectionRange = function(a, c, d) {
        if (a.setSelectionRange) {
            a.focus();
            a.setSelectionRange(c, d)
        } else if (a.createTextRange) {
            var b = a.createTextRange();
            b.collapse(e);
            b.moveEnd(f, d);
            b.moveStart(f, c);
            b.select()
        }
    };
    a.SetCaretToPosition = function(b, a) {
        this.SetSelectionRange(b, a, a)
    };
    a.addClass = function(d, c) {
        var b = d.className.split(" ");
        for (var a = 0; a < b.length; a++) if (c == b[a]) return;
        d.className += " " + c
    };
    a.removeClass = function(c, d) {
        var b = c.className.split(" ");
        c.className = "";
        for (var a = 0; a < b.length; a++) if (d != b[a]) {
            c.className += b[a];
            if (a == b.length - 1) c.className += " "
        }
    };
    a.IsBrowserIE = function() {
        var a = window.navigator.userAgent;
        if (a.indexOf("MSIE") > 0) return e;
        if ( !! a.match(/Trident\/7\./)) return e;
        return d
    };
    a.getPosition = function(a) {
        var b = 0,
        c = 0;
        while (a && !isNaN(a.offsetLeft) && !isNaN(a.offsetTop)) {
            b += a.offsetLeft - a.scrollLeft;
            c += a.offsetTop - a.scrollTop;
            a = a.offsetParent
        }
        return {
            top: c,
            left: b
        }
    };
    a.IsElementInViewport = function(b) {
        var a = b.getBoundingClientRect();
        return a.top >= 0 && a.left >= 0 && a.bottom <= (window.innerHeight || document.documentElement.clientHeight) && a.right <= (window.innerWidth || document.documentElement.clientWidth)
    };
    return a
}
var Util = new CUtil;
var MtPopUpList = function() {
    var a = this;
    a.onChanged = null;
    a.shiftKeyDown = false;
    a.MRUL = [];
    a.MAX_MRUL = 2
};
MtPopUpList.prototype = {
    keysBuffer: "",
    Init: function(d, c, i, g, h) {
        var a = this;
        a.Items = [];
        a.Keys = [];
        a.KeyMap = " " + c.join(" ") + " ";
        a.keysBuffer = "";
        var f = 0;
        for (var b = 0; b < c.length; b++) {
            a.Items[c[b]] = i[b];
            if (c[b] != "-") {
                a.Keys[f] = c[b];
                f++
            }
        }
        a.onChanged = g;
        document.onclick = a.HideCurrentPopup;
        a.elemHeader = Util.GetElement("__" + d + "_header");
        a.elemSvID = Util.GetElement(d + "_svid");
        a.elemTextId = Util.GetElement(d + "_textid");
        a.elemPopup = document.getElementById(h);
        a.cropText();
        if (a.elemPopup != null) {
            a.elemPopup.onkeydown = (new a.doKeyDown(a, a.HideCurrentPopup)).execute;
            a.elemPopup.onkeyup = (new a.doKeyUp(a)).execute;
            a.elemPopup.onkeypress = (new a.doKeyPress(a)).execute
        }
        a.name = d;
        a.mrul_cookie = d + "_lpmru";
        var e = Util.GetCookie(a.mrul_cookie);
        if (e != null && e != "undefined") a.MRUL = e.split(",");
        else a.MRUL = []
    },
    getLinks: function() {
        return this.elemPopup.getElementsByTagName("a")
    },
    getActiveLink: function() {
        var a = this.getLinks(),
        c = this.elemSvID.value;
        if (c != null) for (var b = 0; b < a.length; b++) if (a[b].href.match("#" + c + "$") != null) return a[b];
        return a[0]
    },
    getByLetter: function(i, h, e) {
        var d = this,
        g = String.fromCharCode(h).toUpperCase(),
        f = d.getActiveLink(),
        a = [],
        b;
        for (b = 0; b < e.length; b++) a[b] = e[b];
        a.sort(function(c, d) {
            var a = c.innerText || c.textContent,
            b = d.innerText || d.textContent;
            if (a < b) return - 1;
            if (a > b) return 1;
            return 0
        });
        var c = 0;
        for (; c < a.length; c++) if (f == a[c]) {
            c++;
            break
        }
        for (; c < a.length; c++) if (d.getFirstChar(a[c]) == g) return d.getHref(a[c]);
        for (b = 0; b < a.length; b++) if (d.getFirstChar(a[b]) == g && f != a[b]) return d.getHref(a[b]);
        return null
    },
    getFirstChar: function(b) {
        var a = b.innerText || b.textContent;
        if (a != undefined && a != null && a.length > 0) return a.substr(0, 1).toUpperCase();
        else return ""
    },
    getNextKey: function(e, d) {
        var b = this,
        a = 0;
        for (var c = 0; c < b.Keys.length; c++) if (b.Keys[c] == e) {
            a = c;
            break
        }
        a = a + d;
        if (a > b.Keys.length) a = 0;
        else if (a < 0) a = b.Keys.length - 1;
        return b.Keys[a]
    },
    getNextSibling: function(g, f) {
        var e = this.getActiveLink(),
        c = e.parentNode;
        while (c.tagName.toLowerCase() != "tr" && c.parentNode != null) c = c.parentNode;
        var b = c.getElementsByTagName("a"),
        a = 0;
        for (var d = 0; d < b.length; d++) if (e.href == b[d].href) {
            a = d;
            break
        }
        a = a + f;
        if (a < 0) a = 0;
        else if (a >= b.length) a = b.length - 1;
        return this.getHref(b[a])
    },
    doKeyUp: function(a) {
        this.execute = function(b) {
            if (!b) b = window.event;
            if (b.keyCode == 16) {
                a.shiftKeyDown = false;
                if (b.preventDefault) b.preventDefault();
                else b.returnValue = false;
                b.cancelBubble = true;
                return true
            } else return false
        }
    },
    doKeyPress: function(a) {
        this.execute = function(b) {
            if (!b) b = window.event;
            a.keysBuffer += String.fromCharCode(b.charCode || b.keyCode).toLowerCase();
            clearTimeout(a.keyTimeOut);
            a.keyTimeOut = setTimeout(function() {
                a.keysBuffer = ""
            },
            1e3)
        }
    },
    doKeyDown: function(a, b) {
        this.execute = function(e) {
            var c = false,
            d = null;
            if (!e) e = window.event;
            var g = a.getLinks(),
            f = a.elemSvID.value,
            j = c;
            switch (e.keyCode) {
            case 16:
                a.shiftKeyDown = true;
                return c;
            case 9:
                if (a.shiftKeyDown) d = a.getNextKey(f, -1);
                else d = a.getNextKey(f, 1);
                break;
            case 40:
                d = a.getNextKey(f, 1);
                break;
            case 38:
                d = a.getNextKey(f, -1);
                break;
            case 39:
                d = a.getNextSibling(f, 1);
                break;
            case 37:
                d = a.getNextSibling(f, -1);
                break;
            case 13:
            case 27:
                b();
                return c;
            default:
                j = true
            }
            if (!j) {
                var i = g[0];
                for (var h = 0; h < g.length; h++) if (g[h].href.indexOf("#" + d) != -1) {
                    i = g[h];
                    break
                }
                try {
                    i.focus();
                    i.onclick()
                } catch(k) {}
                return c
            } else {
                window.evt = e;
                setTimeout(function() {
                    if (!e) e = window.evt;
                    var c = a.getLinks(),
                    d;
                    for (var b = 0; b < c.length; b++) {
                        var f = c[b].outerText || c[b].text;
                        if (f.toLowerCase().indexOf(a.keysBuffer) == 0 && f != (a.getActiveLink().outerText || a.getActiveLink().text)) {
                            d = c[b];
                            break
                        }
                    }
                    try {
                        if (d) {
                            d.focus();
                            d.onclick()
                        }
                    } catch(g) {}
                },
                30)
            }
            return true
        }
    },
    Hide: function() {
        this.HideCurrentPopup()
    },
    Show: function(c, b) {
        var d = true,
        a = this;
        if (b) {
            if (b.keyCode == 27) {
                a.Hide(c, b);
                return d
            }
            if (b.keyCode && b.keyCode != 40) return false;
            if (window.curDisplayedPopup == c) {
                a.HideCurrentPopup();
                return d
            }
            a.HideCurrentPopup();
            b.cancelBubble = d;
            if (a.ChangeObjectDisplay(c, "block")) {
                window.curDisplayedDDHeader = a.elemHeader;
                window.curDisplayedPopup = c;
                a.getActiveLink().focus();
                Util.addClass(a.elemHeader, "DDSActive");
                return d
            }
        }
        return false
    },
    cropText: function() {
        var c = "overflow",
        a = this,
        f = "...",
        b = a.elemHeader.innerHTML;
        a.elemHeader.title = b;
        a.elemHeader.innerHTML += "____";
        a.elemHeader.style[c] = "hidden";
        var g = a.elemHeader.clientWidth,
        h = a.elemHeader.scrollWidth,
        d = g * 1 / h * 1;
        if (d < 1) {
            var e = Math.ceil(d * b.length);
            if (e < b.length) b = String(b).substring(0, e - f.length) + f
        }
        a.elemHeader.style[c] = "visible";
        a.elemHeader.innerHTML = b
    },
    getHref: function(a) {
        return a.href.substr(a.href.indexOf("#") + 1)
    },
    setValue: function(b, f) {
        var a = this;
        if (b) {
            var c = (new RegExp(" (" + b + ") ", "i")).exec(a.KeyMap);
            if (c && c[1]) b = c[1]
        }
        if (a.Items[b] == null) throw new Error("Value is not in the current list.");
        a.elemSvID.value = b;
        a.elemHeader.value = a.Items[b];
        if (f != "true") a.addMRUL(b);
        var e = document.getElementById(a.name);
        if (e.tagName == "SELECT") for (var d = 0; d < e.options.length; d++) {
            var g = e.options[d];
            if (g.value == b) {
                g.selected = "selected";
                break
            }
        }
        a.setText(a.Items[b], f)
    },
    getValue: function() {
        return this.elemSvID.value
    },
    setText: function(c, d) {
        var a = this,
        b = document.getElementById(a.name);
        if (b.tagName.toLowerCase() == "select") if (b.value == "") b.options[0].text = c;
        else if (b.options[0].value == "") b.options[0].text = a.Items[""];
        a.elemTextId.value = c;
        a.elemHeader.innerHTML = c;
        a.cropText();
        if (d != "true") a.onChanged(c, a.Items[c])
    },
    getText: function() {
        return this.elemTextId.value
    },
    onclick: function(a) {
        this.setValue(a);
        return false
    },
    ondragstart: function(a) {
        if (!a) a = window.event;
        if (a.preventDefault) a.preventDefault()
    },
    OnSelectedValueChanged: function() {
        return this.onChanged
    },
    HideCurrentPopup: function() {
        if (window.curDisplayedPopup) {
            Util.GetElement(window.curDisplayedPopup).style.display = "none";
            Util.removeClass(window.curDisplayedDDHeader, "DDSActive");
            window.curDisplayedPopup = false;
            window.curDisplayedDDHeader = null
        }
        this.shiftKeyDown = false
    },
    ChangeObjectDisplay: function(c, b) {
        var a = Util.GetStyleObject(c);
        if (a && a.display) {
            a.display = b;
            return true
        } else return false
    },
    addMRUL: function(d) {
        var a = this;
        if (!d) return;
        if (a.MRUL[0] == d) return;
        var c = 0,
        b;
        for (b = 1; b < a.MRUL.length; b++) if (a.MRUL[b] == d) {
            c = b;
            break
        }
        if (c == 0) a.MRUL.unshift(d);
        else {
            var e = c > 0 ? a.MRUL[c] : d;
            for (b = c; b > 0; b--) a.MRUL[b] = a.MRUL[b - 1];
            a.MRUL[0] = e
        }
        while (a.MRUL.length > a.MAX_MRUL) a.MRUL.pop();
        Util.SetCookie(a.mrul_cookie, a.MRUL, true, Util.GetPath())
    }
};
window['_mstConfig'].floaterStylePath = 'https://www.microsofttranslator.com/static/25550255/css/WidgetV3.css?v=25550255';
window['_mstConfig'].translateWithBing = '通过 {0} 翻译';
window['_mstConfig'].withBing = '通过 {0}';
window['_mstConfig'].autoDetected = '{0}（已自动检测）';
function loadAllScripts(fn) {
    var intervalID = setInterval(function() {
        if (document.readyState != 'complete') return;
        clearInterval(intervalID);
        fn();
    },
    10);
}
function onloadCallback() {
    var head = document.getElementsByTagName('head')[0];
    try {
        var body = document.getElementsByTagName('body')[0];
        var numChildren = body.children.length;
        var numScripts = body.getElementsByTagName('script').length;
        function appendHTMLToBody(html) {
            var temp = document.createElement('div');
            temp.innerHTML = html;
            for (var i = 0; i < temp.children.length; i++) {
                body.appendChild(temp.children[i]);
            }
        }
        appendHTMLToBody(decodeURIComponent('%3ctitle%3e%20%3c%2ftitle%3e'));
        appendHTMLToBody(decodeURIComponent('%20%3cdiv%20id%3d%22WidgetFloaterPanels%22%20translate%3d%22no%22%20style%3d%22display%3a%20none%3btext-align%3a%20left%3bdirection%3a%20ltr%22%20class%3d%22LTRStyle%22%20%3e%20%3cdiv%20id%3d%22WidgetFloater%22%20style%3d%22display%3a%20none%22%20%3e%20%3cdiv%20id%3d%22WidgetLogoPanel%22%3e%20%3cspan%20id%3d%22WidgetTranslateWithSpan%22%20style%3d%22text-align%3aleft%3b%22%3e%e5%bf%85%e5%ba%94%e5%9c%a8%e7%ba%bf%e7%bf%bb%e8%af%91%3c%2fspan%3e%20%3cspan%20id%3d%22WidgetCloseButton%22%20title%3d%22%e9%80%80%e5%87%ba%e7%bf%bb%e8%af%91%22%20onclick%3d%22Microsoft.Translator.FloaterOnClose()%22%3e%3cimg%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fclose_x.png%22%20id%3d%22WidgetCloseImage%22%20class%3d%22WidgetCloseImage%22%20%2f%3e%3c%2fspan%3e%3c%2fdiv%3e%20%3cdiv%20id%3d%22LanguageMenuPanel%22%3e%20%3cdiv%20class%3d%22DDStyle_outer%22%3e%3cinput%20name%3d%22LanguageMenu_svid%22%20type%3d%22text%22%20id%3d%22LanguageMenu_svid%22%20style%3d%22display%3anone%3b%22%20autocomplete%3d%22on%22%20value%3d%22zh-CHS%22%20%2f%3e%20%3cinput%20name%3d%22LanguageMenu_textid%22%20type%3d%22text%22%20id%3d%22LanguageMenu_textid%22%20style%3d%22display%3anone%3b%22%20autocomplete%3d%22on%22%20%2f%3e%20%3cspan%20onselectstart%3d%22return%20false%22%20tabindex%3d%220%22%20class%3d%22DDStyle%22%20id%3d%22__LanguageMenu_header%22%20onclick%3d%22return%20LanguageMenu%20%26amp%3b%26amp%3b%20!LanguageMenu.Show(%26%2339%3b__LanguageMenu_popup%26%2339%3b%2c%20event)%3b%22%20onkeydown%3d%22return%20LanguageMenu%20%26amp%3b%26amp%3b%20!LanguageMenu.Show(%26%2339%3b__LanguageMenu_popup%26%2339%3b%2c%20event)%3b%22%3e%e7%ae%80%e4%bd%93%e4%b8%ad%e6%96%87%3c%2fspan%3e%20%3cdiv%20style%3d%22position%3arelative%3btext-align%3aleft%3bleft%3a0%3b%22%3e%3cdiv%20style%3d%22position%3aabsolute%3bwidth%3a%3bleft%3a0px%3b%22%3e%3cdiv%20class%3d%22DDStyle%22%20style%3d%22display%3anone%3b%22%20id%3d%22__LanguageMenu_popup%22%3e%20%3ctable%20id%3d%22LanguageMenu%22%20border%3d%220%22%3e%20%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bar%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ar%22%3e%e9%98%bf%e6%8b%89%e4%bc%af%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bcs%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23cs%22%3e%e6%8d%b7%e5%85%8b%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsw%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sw%22%3e%e6%96%af%e7%93%a6%e5%b8%8c%e9%87%8c%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bet%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23et%22%3e%e7%88%b1%e6%b2%99%e5%b0%bc%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3botq%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23otq%22%3e%e5%85%8b%e9%9b%b7%e5%a1%94%e7%bd%97%e5%a5%a5%e6%89%98%e7%b1%b3%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bty%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ty%22%3e%e5%a1%94%e5%b8%8c%e6%8f%90%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bmww%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23mww%22%3e%e7%99%bd%e8%8b%97%e6%96%87%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3btlh%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23tlh%22%3e%e5%85%8b%e6%9e%97%e8%b4%a1%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bta%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ta%22%3e%e6%b3%b0%e7%b1%b3%e5%b0%94%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bbg%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23bg%22%3e%e4%bf%9d%e5%8a%a0%e5%88%a9%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bhr%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23hr%22%3e%e5%85%8b%e7%bd%97%e5%9c%b0%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bth%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23th%22%3e%e6%b3%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bis%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23is%22%3e%e5%86%b0%e5%b2%9b%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3blv%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23lv%22%3e%e6%8b%89%e8%84%b1%e7%bb%b4%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bto%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23to%22%3e%e6%b1%a4%e5%8a%a0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bpl%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23pl%22%3e%e6%b3%a2%e5%85%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3blt%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23lt%22%3e%e7%ab%8b%e9%99%b6%e5%ae%9b%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3btr%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23tr%22%3e%e5%9c%9f%e8%80%b3%e5%85%b6%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bbs-Latn%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23bs-Latn%22%3e%e6%b3%a2%e6%96%af%e5%b0%bc%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bro%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ro%22%3e%e7%bd%97%e9%a9%ac%e5%b0%bc%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bcy%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23cy%22%3e%e5%a8%81%e5%b0%94%e5%a3%ab%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bfa%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23fa%22%3e%e6%b3%a2%e6%96%af%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bmg%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23mg%22%3e%e9%a9%ac%e5%b0%94%e5%8a%a0%e4%bb%80%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bur%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ur%22%3e%e4%b9%8c%e5%b0%94%e9%83%bd%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bko%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ko%22%3e%e6%9c%9d%e9%b2%9c%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bmt%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23mt%22%3e%e9%a9%ac%e8%80%b3%e4%bb%96%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3buk%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23uk%22%3e%e4%b9%8c%e5%85%8b%e5%85%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bda%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23da%22%3e%e4%b8%b9%e9%ba%a6%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bms%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ms%22%3e%e9%a9%ac%e6%9d%a5%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bes%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23es%22%3e%e8%a5%bf%e7%8f%ad%e7%89%99%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bde%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23de%22%3e%e5%be%b7%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bbn%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23bn%22%3e%e5%ad%9f%e5%8a%a0%e6%8b%89%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bhe%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23he%22%3e%e5%b8%8c%e4%bc%af%e6%9d%a5%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bru%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ru%22%3e%e4%bf%84%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3baf%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23af%22%3e%e5%8d%97%e9%9d%9e%e8%8d%b7%e5%85%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bel%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23el%22%3e%e5%b8%8c%e8%85%8a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bfr%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23fr%22%3e%e6%b3%95%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bno%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23no%22%3e%e6%8c%aa%e5%a8%81%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bhu%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23hu%22%3e%e5%8c%88%e7%89%99%e5%88%a9%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bzh-CHT%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23zh-CHT%22%3e%e7%b9%81%e4%bd%93%e4%b8%ad%e6%96%87%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bpt%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23pt%22%3e%e8%91%a1%e8%90%84%e7%89%99%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bit%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23it%22%3e%e6%84%8f%e5%a4%a7%e5%88%a9%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bfil%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23fil%22%3e%e8%8f%b2%e5%be%8b%e5%ae%be%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bja%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ja%22%3e%e6%97%a5%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bhi%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23hi%22%3e%e5%8d%b0%e5%9c%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bfj%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23fj%22%3e%e6%96%90%e6%b5%8e%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsv%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sv%22%3e%e7%91%9e%e5%85%b8%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bid%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23id%22%3e%e5%8d%b0%e5%ba%a6%e5%b0%bc%e8%a5%bf%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bfi%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23fi%22%3e%e8%8a%ac%e5%85%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsm%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sm%22%3e%e8%90%a8%e6%91%a9%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3ben%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23en%22%3e%e8%8b%b1%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bht%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ht%22%3e%e6%b5%b7%e5%9c%b0%e5%85%8b%e9%87%8c%e5%a5%a5%e5%b0%94%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsr-Latn%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sr-Latn%22%3e%e5%a1%9e%e5%b0%94%e7%bb%b4%e4%ba%9a%e8%af%ad%20(%e6%8b%89%e4%b8%81%e6%96%87)%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3byua%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23yua%22%3e%e5%b0%a4%e5%8d%a1%e5%9d%a6%e7%8e%9b%e9%9b%85%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bnl%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23nl%22%3e%e8%8d%b7%e5%85%b0%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsr-Cyrl%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sr-Cyrl%22%3e%e5%a1%9e%e5%b0%94%e7%bb%b4%e4%ba%9a%e8%af%ad%20(%e8%a5%bf%e9%87%8c%e5%b0%94%e6%96%87)%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bvi%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23vi%22%3e%e8%b6%8a%e5%8d%97%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bca%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23ca%22%3e%e5%8a%a0%e6%b3%b0%e9%9a%86%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsk%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sk%22%3e%e6%96%af%e6%b4%9b%e4%bc%90%e5%85%8b%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3byue%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23yue%22%3e%e7%b2%a4%e8%af%ad(%e7%b9%81%e4%bd%93)%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%3ctr%3e%20%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bzh-CHS%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23zh-CHS%22%3e%e7%ae%80%e4%bd%93%e4%b8%ad%e6%96%87%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bsl%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23sl%22%3e%e6%96%af%e6%b4%9b%e6%96%87%e5%b0%bc%e4%ba%9a%e8%af%ad%3c%2fa%3e%3c%2ftd%3e%3ctd%3e%3ca%20tabindex%3d%22-1%22%20onclick%3d%22return%20LanguageMenu.onclick(%26%2339%3bzh-chs%26%2339%3b)%3b%22%20ondragstart%3d%22LanguageMenu.ondragstart(event)%3b%22%20href%3d%22%23zh-chs%22%3e%e7%ae%80%e4%bd%93%e4%b8%ad%e6%96%87%3c%2fa%3e%3c%2ftd%3e%20%3c%2ftr%3e%20%3c%2ftable%3e%20%3cimg%20alt%3d%22%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fniche.gif%22%20style%3d%22height%3a7px%3bwidth%3a17px%3bborder-width%3a0px%3bleft%3a20px%3b%22%20%2f%3e%20%3c%2fdiv%3e%3c%2fdiv%3e%3c%2fdiv%3e%3c%2fdiv%3e%20%3cscript%20type%3d%22text%2fjavascript%22%3e%20var%20LanguageMenu%3b%20var%20LanguageMenu_keys%3d%5b%22ar%22%2c%22et%22%2c%22mww%22%2c%22bg%22%2c%22is%22%2c%22pl%22%2c%22bs-Latn%22%2c%22fa%22%2c%22ko%22%2c%22da%22%2c%22de%22%2c%22ru%22%2c%22fr%22%2c%22zh-CHT%22%2c%22fil%22%2c%22fj%22%2c%22fi%22%2c%22ht%22%2c%22nl%22%2c%22ca%22%2c%22zh-CHS%22%2c%22cs%22%2c%22otq%22%2c%22tlh%22%2c%22hr%22%2c%22lv%22%2c%22lt%22%2c%22ro%22%2c%22mg%22%2c%22mt%22%2c%22ms%22%2c%22bn%22%2c%22af%22%2c%22no%22%2c%22pt%22%2c%22ja%22%2c%22sv%22%2c%22sm%22%2c%22sr-Latn%22%2c%22sr-Cyrl%22%2c%22sk%22%2c%22sl%22%2c%22sw%22%2c%22ty%22%2c%22ta%22%2c%22th%22%2c%22to%22%2c%22tr%22%2c%22cy%22%2c%22ur%22%2c%22uk%22%2c%22es%22%2c%22he%22%2c%22el%22%2c%22hu%22%2c%22it%22%2c%22hi%22%2c%22id%22%2c%22en%22%2c%22yua%22%2c%22vi%22%2c%22yue%22%2c%22zh-chs%22%5d%3b%20var%20LanguageMenu_values%3d%5b%22%e9%98%bf%e6%8b%89%e4%bc%af%e8%af%ad%22%2c%22%e7%88%b1%e6%b2%99%e5%b0%bc%e4%ba%9a%e8%af%ad%22%2c%22%e7%99%bd%e8%8b%97%e6%96%87%22%2c%22%e4%bf%9d%e5%8a%a0%e5%88%a9%e4%ba%9a%e8%af%ad%22%2c%22%e5%86%b0%e5%b2%9b%e8%af%ad%22%2c%22%e6%b3%a2%e5%85%b0%e8%af%ad%22%2c%22%e6%b3%a2%e6%96%af%e5%b0%bc%e4%ba%9a%e8%af%ad%22%2c%22%e6%b3%a2%e6%96%af%e8%af%ad%22%2c%22%e6%9c%9d%e9%b2%9c%e8%af%ad%22%2c%22%e4%b8%b9%e9%ba%a6%e8%af%ad%22%2c%22%e5%be%b7%e8%af%ad%22%2c%22%e4%bf%84%e8%af%ad%22%2c%22%e6%b3%95%e8%af%ad%22%2c%22%e7%b9%81%e4%bd%93%e4%b8%ad%e6%96%87%22%2c%22%e8%8f%b2%e5%be%8b%e5%ae%be%e8%af%ad%22%2c%22%e6%96%90%e6%b5%8e%22%2c%22%e8%8a%ac%e5%85%b0%e8%af%ad%22%2c%22%e6%b5%b7%e5%9c%b0%e5%85%8b%e9%87%8c%e5%a5%a5%e5%b0%94%e8%af%ad%22%2c%22%e8%8d%b7%e5%85%b0%e8%af%ad%22%2c%22%e5%8a%a0%e6%b3%b0%e9%9a%86%e8%af%ad%22%2c%22%e7%ae%80%e4%bd%93%e4%b8%ad%e6%96%87%22%2c%22%e6%8d%b7%e5%85%8b%e8%af%ad%22%2c%22%e5%85%8b%e9%9b%b7%e5%a1%94%e7%bd%97%e5%a5%a5%e6%89%98%e7%b1%b3%e8%af%ad%22%2c%22%e5%85%8b%e6%9e%97%e8%b4%a1%e8%af%ad%22%2c%22%e5%85%8b%e7%bd%97%e5%9c%b0%e4%ba%9a%e8%af%ad%22%2c%22%e6%8b%89%e8%84%b1%e7%bb%b4%e4%ba%9a%e8%af%ad%22%2c%22%e7%ab%8b%e9%99%b6%e5%ae%9b%e8%af%ad%22%2c%22%e7%bd%97%e9%a9%ac%e5%b0%bc%e4%ba%9a%e8%af%ad%22%2c%22%e9%a9%ac%e5%b0%94%e5%8a%a0%e4%bb%80%e8%af%ad%22%2c%22%e9%a9%ac%e8%80%b3%e4%bb%96%e8%af%ad%22%2c%22%e9%a9%ac%e6%9d%a5%e8%af%ad%22%2c%22%e5%ad%9f%e5%8a%a0%e6%8b%89%e8%af%ad%22%2c%22%e5%8d%97%e9%9d%9e%e8%8d%b7%e5%85%b0%e8%af%ad%22%2c%22%e6%8c%aa%e5%a8%81%e8%af%ad%22%2c%22%e8%91%a1%e8%90%84%e7%89%99%e8%af%ad%22%2c%22%e6%97%a5%e8%af%ad%22%2c%22%e7%91%9e%e5%85%b8%e8%af%ad%22%2c%22%e8%90%a8%e6%91%a9%e4%ba%9a%e8%af%ad%22%2c%22%e5%a1%9e%e5%b0%94%e7%bb%b4%e4%ba%9a%e8%af%ad%20(%e6%8b%89%e4%b8%81%e6%96%87)%22%2c%22%e5%a1%9e%e5%b0%94%e7%bb%b4%e4%ba%9a%e8%af%ad%20(%e8%a5%bf%e9%87%8c%e5%b0%94%e6%96%87)%22%2c%22%e6%96%af%e6%b4%9b%e4%bc%90%e5%85%8b%e8%af%ad%22%2c%22%e6%96%af%e6%b4%9b%e6%96%87%e5%b0%bc%e4%ba%9a%e8%af%ad%22%2c%22%e6%96%af%e7%93%a6%e5%b8%8c%e9%87%8c%e8%af%ad%22%2c%22%e5%a1%94%e5%b8%8c%e6%8f%90%e8%af%ad%22%2c%22%e6%b3%b0%e7%b1%b3%e5%b0%94%e8%af%ad%22%2c%22%e6%b3%b0%e8%af%ad%22%2c%22%e6%b1%a4%e5%8a%a0%e8%af%ad%22%2c%22%e5%9c%9f%e8%80%b3%e5%85%b6%e8%af%ad%22%2c%22%e5%a8%81%e5%b0%94%e5%a3%ab%e8%af%ad%22%2c%22%e4%b9%8c%e5%b0%94%e9%83%bd%e8%af%ad%22%2c%22%e4%b9%8c%e5%85%8b%e5%85%b0%e8%af%ad%22%2c%22%e8%a5%bf%e7%8f%ad%e7%89%99%e8%af%ad%22%2c%22%e5%b8%8c%e4%bc%af%e6%9d%a5%e8%af%ad%22%2c%22%e5%b8%8c%e8%85%8a%e8%af%ad%22%2c%22%e5%8c%88%e7%89%99%e5%88%a9%e8%af%ad%22%2c%22%e6%84%8f%e5%a4%a7%e5%88%a9%e8%af%ad%22%2c%22%e5%8d%b0%e5%9c%b0%e8%af%ad%22%2c%22%e5%8d%b0%e5%ba%a6%e5%b0%bc%e8%a5%bf%e4%ba%9a%e8%af%ad%22%2c%22%e8%8b%b1%e8%af%ad%22%2c%22%e5%b0%a4%e5%8d%a1%e5%9d%a6%e7%8e%9b%e9%9b%85%e8%af%ad%22%2c%22%e8%b6%8a%e5%8d%97%e8%af%ad%22%2c%22%e7%b2%a4%e8%af%ad(%e7%b9%81%e4%bd%93)%22%2c%22%e7%ae%80%e4%bd%93%e4%b8%ad%e6%96%87%22%5d%3b%20var%20LanguageMenu_callback%3dfunction()%7b%20%7d%3b%20var%20LanguageMenu_popupid%3d%27__LanguageMenu_popup%27%3b%20%3c%2fscript%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22CTFLinksPanel%22%3e%20%3cspan%20id%3d%22ExternalLinksPanel%22%3e%3ca%20id%3d%22BingTranslatorLink%22%20title%3d%22bing.com%2ftranslator%22%20href%3d%22https%3a%2f%2fwww.bing.com%2ftranslator%22%20target%3d%22_blank%22%3e%20%3cimg%20id%3d%22BingTranslatorLinkImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fbingmark.png%22%20%2f%3e%3c%2fa%3e%20%3ca%20id%3d%22FacebookLink%22%20href%3d%22https%3a%2f%2fwww.facebook.com%2fmicrosofttranslator%22%20title%3d%22Facebook%20%e5%bf%85%e5%ba%94%e5%9c%a8%e7%ba%bf%e7%bf%bb%e8%af%91%22%20target%3d%22_blank%22%3e%20%3cimg%20id%3d%22FacebookImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2ffbookmark.png%22%20%2f%3e%3c%2fa%3e%20%3ca%20id%3d%22ShareLink%22%20title%3d%22%e5%88%86%e4%ba%ab%22%20href%3d%22javascript%3aMicrosoft.Translator.FloaterShowSharePanel()%22%3e%20%3cimg%20id%3d%22ShareImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fsharemark.png%22%20%2f%3e%3c%2fa%3e%20%3c%2fspan%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22FloaterProgressBar%22%3e%20%3cdiv%20id%3d%22ProgressFill%22%20%3e%20%e6%ad%a3%e5%9c%a8%e7%bf%bb%e8%af%91...%20%3c%2fdiv%3e%20%3c%2fdiv%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22WidgetFloaterCollapsed%22%20style%3d%22display%3a%20none%22%3e%20%3cspan%20id%3d%22WidgetFloaterCollapsedSpan%22%3e%e5%bf%85%e5%ba%94%e5%9c%a8%e7%ba%bf%e7%bf%bb%e8%af%91%3c%2fspan%3e%20%3cspan%20id%3d%22WidgetCloseButtonCollapsed%22%20title%3d%22%e9%80%80%e5%87%ba%e7%bf%bb%e8%af%91%22%20onclick%3d%22Microsoft.Translator.FloaterOnClose()%22%3e%3cimg%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fclose_x.png%22%20id%3d%22WidgetCloseImageCollapsed%22%20class%3d%22WidgetCloseImage%22%20%2f%3e%3c%2fspan%3e%3c%2fdiv%3e%20%3cdiv%20id%3d%22FloaterSharePanel%22%20style%3d%22display%3a%20none%22%20%3e%20%3cdiv%20id%3d%22ShareTextDiv%22%3e%20%3cspan%20id%3d%22ShareTextSpan%22%3e%20%e5%a4%8d%e5%88%b6%e4%b8%8b%e9%9d%a2%e7%9a%84%20URL%20%3c%2fspan%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22ShareTextboxDiv%22%3e%20%3cinput%20name%3d%22ShareTextbox%22%20type%3d%22text%22%20id%3d%22ShareTextbox%22%20readonly%3d%22readonly%22%20%2f%3e%20%3c!--a%20id%3d%22TwitterLink%22%20title%3d%22%e5%9c%a8%20Twitter%20%e4%b8%8a%e5%85%b1%e4%ba%ab%22%3e%20%3cimg%20id%3d%22TwitterImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2ftwitter_icon.png%22%20%2f%3e%3c%2fa%3e%20%3ca--%20id%3d%22FacebookLink%22%20title%3d%22%e5%9c%a8%20Facebook%20%e4%b8%8a%e5%85%b1%e4%ba%ab%22%3e%20%3cimg%20id%3d%22FacebookImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2ffacebook_icon.png%22%20%2f%3e%3c%2fa--%3e%20%3ca%20id%3d%22EmailLink%22%20title%3d%22%e9%80%9a%e8%bf%87%e7%94%b5%e5%ad%90%e9%82%ae%e4%bb%b6%e5%8f%91%e9%80%81%e6%ad%a4%e7%bf%bb%e8%af%91%22%3e%20%3cimg%20id%3d%22EmailImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2femail_icon.png%22%20%2f%3e%3c%2fa%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22ShareFooter%22%3e%20%3cspan%20id%3d%22ShareHelpSpan%22%3e%3ca%20id%3d%22ShareHelpLink%22%3e%20%3cimg%20id%3d%22ShareHelpImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fembed_question.png%22%20%2f%3e%3c%2fa%3e%3c%2fspan%3e%20%3cspan%20id%3d%22ShareBackSpan%22%3e%3ca%20id%3d%22ShareBack%22%20href%3d%22javascript%3aMicrosoft.Translator.FloaterOnShareBackClick()%22%20title%3d%22%e8%bf%94%e5%9b%9e%e7%bf%bb%e8%af%91%22%3e%20%e8%bf%94%e5%9b%9e%3c%2fa%3e%3c%2fspan%3e%20%3c%2fdiv%3e%20%3cinput%20name%3d%22EmailSubject%22%20type%3d%22hidden%22%20id%3d%22EmailSubject%22%20value%3d%22%e6%9f%a5%e7%9c%8b%e6%ad%a4%e9%a1%b5%e4%bb%8e%20%7b1%7d%20%e5%88%b0%20%7b0%7d%20%e7%9a%84%e7%bf%bb%e8%af%91%22%20%2f%3e%20%3cinput%20name%3d%22EmailBody%22%20type%3d%22hidden%22%20id%3d%22EmailBody%22%20value%3d%22%e8%af%91%e6%96%87%3a%20%7b0%7d%250d%250a%e5%8e%9f%e6%96%87%3a%20%7b1%7d%250d%250a%250d%250a%e8%87%aa%e5%8a%a8%e7%bf%bb%e8%af%91%e7%94%b1%20Microsoft%c2%ae%20Translator%20%e6%8f%90%e4%be%9b%250d%250ahttps%3a%2f%2fwww.bing.com%2ftranslator%3fref%3dMSTWidget%22%20%2f%3e%20%3cinput%20type%3d%22hidden%22%20id%3d%22ShareHelpText%22%20value%3d%22%e6%ad%a4%e9%93%be%e6%8e%a5%e5%85%81%e8%ae%b8%e8%ae%bf%e9%97%ae%e8%80%85%e5%90%af%e5%8a%a8%e6%9c%ac%e9%a1%b5%e9%9d%a2%ef%bc%8c%e5%b9%b6%e8%87%aa%e5%8a%a8%e7%bf%bb%e8%af%91%e4%b8%ba%7b0%7d%e3%80%82%22%2f%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22FloaterEmbed%22%20style%3d%22display%3a%20none%22%3e%20%3cdiv%20id%3d%22EmbedTextDiv%22%3e%20%3cspan%20id%3d%22EmbedTextSpan%22%3e%e5%b0%86%e4%b8%8b%e9%9d%a2%e7%9a%84%e4%bb%a3%e7%a0%81%e6%ae%b5%e5%b5%8c%e5%85%a5%e6%82%a8%e7%9a%84%e7%ab%99%e7%82%b9%3c%2fspan%3e%20%3ca%20id%3d%22EmbedHelpLink%22%20title%3d%22%e5%a4%8d%e5%88%b6%e6%ad%a4%e4%bb%a3%e7%a0%81%ef%bc%8c%e5%b9%b6%e5%b0%86%e5%85%b6%e7%bd%ae%e4%ba%8e%e6%82%a8%e7%9a%84%20HTML%20%e4%b8%ad%e3%80%82%22%3e%20%3cimg%20id%3d%22EmbedHelpImg%22%20src%3d%22https%3a%2f%2fwww.microsofttranslator.com%2fstatic%2f25550255%2fimg%2fembed_question.png%22%2f%3e%3c%2fa%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22EmbedTextboxDiv%22%3e%20%3cinput%20name%3d%22EmbedSnippetTextBox%22%20type%3d%22text%22%20id%3d%22EmbedSnippetTextBox%22%20readonly%3d%22readonly%22%20value%3d%22%26lt%3bdiv%20id%3d%26%2339%3bMicrosoftTranslatorWidget%26%2339%3b%20class%3d%26%2339%3bDark%26%2339%3b%20style%3d%26%2339%3bcolor%3awhite%3bbackground-color%3a%23555555%26%2339%3b%3e%26lt%3b%2fdiv%3e%26lt%3bscript%20type%3d%26%2339%3btext%2fjavascript%26%2339%3b%3esetTimeout(function()%7bvar%20s%3ddocument.createElement(%26%2339%3bscript%26%2339%3b)%3bs.type%3d%26%2339%3btext%2fjavascript%26%2339%3b%3bs.charset%3d%26%2339%3bUTF-8%26%2339%3b%3bs.src%3d((location%20%26amp%3b%26amp%3b%20location.href%20%26amp%3b%26amp%3b%20location.href.indexOf(%26%2339%3bhttps%26%2339%3b)%20%3d%3d%200)%3f%26%2339%3bhttps%3a%2f%2fssl.microsofttranslator.com%26%2339%3b%3a%26%2339%3bhttps%3a%2f%2fwww.microsofttranslator.com%26%2339%3b)%2b%26%2339%3b%2fajax%2fv3%2fWidgetV3.ashx%3fsiteData%3dueOIGRSKkd965FeEGM5JtQ**%26amp%3bctf%3dtrue%26amp%3bui%3dtrue%26amp%3bsettings%3dmanual%26amp%3bfrom%3dzh-CHS%26%2339%3b%3bvar%20p%3ddocument.getElementsByTagName(%26%2339%3bhead%26%2339%3b)%5b0%5d%7c%7cdocument.documentElement%3bp.insertBefore(s%2cp.firstChild)%3b%20%7d%2c0)%3b%26lt%3b%2fscript%3e%22%20%2f%3e%20%3c%2fdiv%3e%20%3cdiv%20id%3d%22EmbedNoticeDiv%22%3e%3cspan%20id%3d%22EmbedNoticeSpan%22%3e%e5%90%af%e7%94%a8%e5%8d%8f%e4%bd%9c%e5%8a%9f%e8%83%bd%e5%92%8c%e8%87%aa%e5%ae%9a%e4%b9%89%e5%b0%8f%e5%b7%a5%e5%85%b7%3a%20%3ca%20href%3d%22https%3a%2f%2fwww.bing.com%2fwidget%2ftranslator%22%20target%3d%22_blank%22%3e%e5%bf%85%e5%ba%94%e7%bd%91%e7%ab%99%e7%ae%a1%e7%90%86%e5%91%98%e9%97%a8%e6%88%b7%3c%2fa%3e%3c%2fspan%3e%3c%2fdiv%3e%20%3cdiv%20id%3d%22EmbedFooterDiv%22%3e%3cspan%20id%3d%22EmbedBackSpan%22%3e%3ca%20href%3d%22javascript%3aMicrosoft.Translator.FloaterOnEmbedBackClick()%22%20title%3d%22%e8%bf%94%e5%9b%9e%e7%bf%bb%e8%af%91%22%3e%e8%bf%94%e5%9b%9e%3c%2fa%3e%3c%2fspan%3e%3c%2fdiv%3e%20%3c%2fdiv%3e%20%3cscript%20type%3d%22text%2fjavascript%22%3e%20var%20intervalId%20%3d%20setInterval(function%20()%20%7b%20if%20(MtPopUpList)%20%7b%20LanguageMenu%20%3d%20new%20MtPopUpList()%3b%20var%20langMenu%20%3d%20document.getElementById(LanguageMenu_popupid)%3b%20var%20origLangDiv%20%3d%20document.createElement(%22div%22)%3b%20origLangDiv.id%20%3d%20%22OriginalLanguageDiv%22%3b%20origLangDiv.innerHTML%20%3d%20%22%3cspan%20id%3d%27OriginalTextSpan%27%3e%e5%8e%9f%e6%96%87%3a%20%3c%2fspan%3e%3cspan%20id%3d%27OriginalLanguageSpan%27%3e%3c%2fspan%3e%22%3b%20langMenu.appendChild(origLangDiv)%3b%20LanguageMenu.Init(%27LanguageMenu%27%2c%20LanguageMenu_keys%2c%20LanguageMenu_values%2c%20LanguageMenu_callback%2c%20LanguageMenu_popupid)%3b%20window%5b%22LanguageMenu%22%5d%20%3d%20LanguageMenu%3b%20clearInterval(intervalId)%3b%20%7d%20%7d%2c%201)%3b%20%3c%2fscript%3e%20%3c%2fdiv%3e%20'));
        var code = '';
        var scripts = body.getElementsByTagName('script');
        for (var i = numScripts; i < scripts.length; i++) {
            if (scripts[i].innerHTML.length != 0) {
                code += scripts[i].innerHTML;
            }
        }
        eval(code);
    } catch(e) {
        console.error(e);
    }
    Microsoft.Translator.FloaterInitialize('zh-CHS', 'true', '');
}
loadAllScripts(onloadCallback);