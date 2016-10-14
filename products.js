var pageCount=window.pageCount;
function newObject(nowPage) {
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHttp");
    }

    req.onreadystatechange = press;
    req.open("GET", "Prodata.bin?page=" + nowPage, true);
    req.send("");
}
function press() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            //XML解析程序
            root = req.responseXML.getElementsByTagName("*");
            //测试是否解析到数据
            //alert(root[0].childNodes[1].childNodes[3].childNodes[0].nodeValue);
            //如何操作定位表格元素 第几行第几列
            count = Math.floor((root[0].childNodes.length-2)/2);
            window.pageCount=parseInt(getValue(root[0],"count"));

            for (i = 0; i < count; i++) {
                row =parseInt(i / 4);
                //alert(row);
                cell = i % 4;
                product = root[0].childNodes[2*i+1];
                //alert(product);
                //生成单元格中的HTML语句
                content = "";
                content = "<img src='";
                content += getValue(product, "img");
                content += "' class='bigImg'/>";

                content += "<div class='name'>";
                content += "<a href='' title='";
                content += getValue(product, "info");
                content += "'>";
                content += getValue(product, "name");
                content += "</a></div>";

                content += "<div class='price'>&yen;";
                content += getValue(product, "price");
                content += "</div>";

                content += "已有";
                content += getValue(product, "commentCount");
                content += "人评价";

                if (getValue(product, "gift") != "0") {
                    content += "<a class='p1' title='购买本商品送赠品'><img src='image/gift.jpg'/></a>";
                }

                if (getValue(product, "down") == "y") {
                    content += "<a class='p2' title='本商品正在降价销售中'><img src='image/down.jpg'/></a>";
                }

                content += "<div>";
                content += "<input type='button' value='购买' onclick='location.replace(\"products.jsp?pid=<%=rs.getString(7)%>\");'>";
                content += "<input type='button' value='关注'>";
                content += "<input type='button' value='对比'>";
                content += "</div>";

                t1.rows[row].cells[cell].innerHTML = content;
            }
        }
    }
}
function getValue(product, name) {
    nodeCount = product.childNodes.length;
    for (j = 0; j < nodeCount; j++) {
        if (product.childNodes[j].nodeName == name) {
            if (product.childNodes[j].childNodes[0] != null) {
                return product.childNodes[j].childNodes[0].nodeValue;
            }
        }
    }
}
/**
 * Created by Administrator on 2016/10/10.
 */
