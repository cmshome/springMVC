<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>黑客帝国-字体掉落效果</title>
</head>
<style>
    html, body {
        margin: 0;
        padding: 0;
        background-color: #000000;
        overflow: hidden;
    }

    span {
        position: absolute;
        display: inline-block;
        font-weight: bold;
        font-family: Courier New, sans-serif;
        top: -10px;
        width: 50px;
        word-wrap: break-word;
        letter-spacing: 20px;
    }
</style>

<body></body>

<script>

    //窗口宽高
    var maxWidth = window.innerWidth;
    var maxHeight = window.innerHeight;

    //窗口大小重置时，执行方法
    window.onresize = function () {
        maxWidth = window.innerWidth;
        maxHeight = window.innerHeight;
    };

    /**
     * @param intensity 内容密集度
     */
    function LetterDrop(intensity) {

        this.intensity = intensity;
        //默认展示的内容
        var defaultContent = [
            "please call me big brother", "请 叫 我 大 湿 胸"
        ];
        this.runningDom = [];

        /**
         * 随机字体颜色定义-绿色为主
         */
        var fontColors = [];
        for (var i = 1; i < 17; i++) {
            var f = i.toString(16);
            fontColors.push('#0' + f + '0');
        }

        //#nnnnnn
        function fetchFontColor() {
            return fontColors[fetchRandomNum(0, fontColors.length)];
        }

        /**
         * 获取一个随机字体大小 n px
         */
        function fetchFontSize() {
            return fetchRandomNum(9, 25) + "px";
        }

        /**
         * 获取一个随机数 min ≤ n ≤ max
         */
        function fetchRandomNum(min, max) {
            return Number(min + Math.round(Math.random() * (max - min)));
        }

        /**
         * 获取一个随机 char
         */
        function fetchRandomChart() {
            var chars = fetchRandomNum(0, 100);
            //A-Z :65-90 a-z:97-122,修改成只生成字母
            if (chars < defaultContent.length) {//生成提供的值
                return defaultContent[chars];
            } else if ((chars >= 65 && chars <= 90)) {//生成大写字母
                return String.fromCharCode(chars);
            } else { //生成小写字母
                chars = fetchRandomNum(97, 122);
                return String.fromCharCode(chars)
            }
        }

        /**
         * 创建一个 span
         * @param innerText span 显示内容
         * @param marginLeft marginLeft
         */
        this.createEl = function (innerText, marginLeft) {

            var el = document.createElement('span');
            el.innerText = innerText;
            el.style.marginLeft = marginLeft || (fetchRandomNum(0, maxWidth) + "px");
            el.style.fontSize = fetchFontSize();
            el.style.color = fetchFontColor();
            document.body.appendChild(el);
            this.runningDom.push(el);
        };

        //创建单个字母内容 span
        this.createSingleEl = function () {

            for (var i = 0; i < this.intensity; i++) {
                this.createEl(fetchRandomChart());
            }

        };

        //创建多个纵向显示字母内容 span 内容长度[0-12]
        this.createMultiVerticalEl = function () {
            for (var i = 0; i < this.intensity; i++) {
                var marginLeft = fetchRandomNum(0, maxWidth) + "px";
                var innerText = "";
                for (var y = 0; y < fetchRandomNum(1, 12); y++) {
                    innerText += fetchRandomChart();
                }
                this.createEl(innerText, marginLeft);
            }
        };

        //动态下落span ，移除到达底部的 span
        this.runEl = function () {

            this.runningDom.forEach(function (el, i, runningDom, intensity) {
                var oldTopPx = el.style.top;
                var oldTop = Number(oldTopPx.substring(0, oldTopPx.length - 2));

                if (Math.abs(oldTop - maxHeight) < 30) {
                    runningDom.splice(i, 1);
                    document.body.removeChild(el);
                } else {
                    el.style.top = oldTop + fetchRandomNum(0, 30) + "px";
                }
            });
        };

        this.running = function () {

            var o = this;
            window.setInterval(
                function () {
                    //单个字母span雨
                    o.createSingleEl();
                    //多个字母span雨
                    o.createMultiVerticalEl();
                    o.runEl();
                },
                100
            )
        };

    }

    //密集度默认为 5
    new LetterDrop(4).running();

</script>

</html>
