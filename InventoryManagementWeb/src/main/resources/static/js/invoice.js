
function init() {
    $("#selectedProduct").chosen();
    $(".add-row").click(function () {
        var productName = $("#selectedProduct option:selected").text();
        var productId = $("#selectedProduct").val();
        var quantity = $("#quantity").val();
        var markup = "<tr>"
            + "<td ><input type='button' th:value='#{invoice_page.delete_button}' onclick='$(this).parent().parent().remove();'/>"
            + "<input type='hidden' name=\"productQuantityMap['" + productId + "']\" value='" + quantity + "'/>"
            + "</td>"
            + "<td>" + productName + "</td>"
            + "<td>" + quantity + "</td>"
            + "</tr>";
        $("table tbody").append(markup);
    });
};