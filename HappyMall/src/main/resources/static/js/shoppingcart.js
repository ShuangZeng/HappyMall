/**
 * 
 */

$(document).ready(function() {
	formatMoney();
	calPrice();	
});

// format money
function formatMoney() {
    $(".format-money").each( (i, val) => {
        console.log("format-money", $(val).text());
        let price = parseFloat($(val).text().replace("$","")).toFixed(2);
        $(val).text("$" + price);
    });
}

// calculate the total price
function calPrice() {
	let totalBeforeTax = 0;
	let estimatedTax = 0;
	let orderTotal = 0;
    console.log("calPrice");
    $(".item").each((i, val) => {
        let qty = parseFloat($(val).find(".quantity").val());
        let price = parseFloat($(val).find(".price").text().replace("$",""));
        console.log("price: " + price);
        console.log("quantity: " + qty);
        totalBeforeTax += (qty * price);
        console.log("totalBeforeTax: " + totalBeforeTax);
    });
    estimatedTax = parseFloat(totalBeforeTax * 7 / 100).toFixed(2);
    orderTotal = parseFloat(totalBeforeTax + estimatedTax).toFixed(2);
    
    console.log("totalBeforeTax", totalBeforeTax);
    console.log("estimatedTax", estimatedTax);
    console.log("orderTotal", orderTotal);
    
    $("#totalBeforeTax").text("$" + parseFloat(totalBeforeTax).toFixed(2));
    $("#estimatedTax").text("$" + parseFloat(estimatedTax).toFixed(2));
    $("#orderTotal").text("$" + parseFloat(orderTotal).toFixed(2));
}

