$(function () {
    getPageInfo(0);
})
var current = 0;
var total = 10;
function getPageInfo(currentPage) {
    var url ="/article/getPageArticle";
    var category = $("#articleCatory").val();
    $.post(url, {"category":category}, function (data) {
        alert(data)
    });
}