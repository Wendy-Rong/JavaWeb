function editCart(cartItemId, buyCount) {
    window.location.href = 'cart.do?operate=editCart&cartItemId=' + cartItemId + "&buyCount=" + buyCount;
}


// window.onload = function () {
//     var vue = new Vue({
//         el: "#cart_div",
//         data: {
//             cart: {}
//         },
//         methods: {
//             getCart: function () {
//                 axios({
//                     method: "POST",
//                     url: "cart.do",
//                     params: {
//                         operate: 'cartInfo'
//                     }
//                 })
//                     .then(function (value) {
//                         // console.log(value.data);
//                         var cart = value.data;
//                         vue.cart = cart;//给当前的vue的cart属性赋值
//                     })
//                     .catch(function (reason) {
//                     });
//             },
//             editCart: function (cartItemId, buyCount) {
//                 axios({
//                     method: "POST",
//                     url: "cart.do",
//                     params: {
//                         operate: 'editCart',
//                         cartItemId: cartItemId,
//                         buyCount: buyCount
//                     }
//                 })
//                     .then(function (value) {
//                         vue.getCart();
//                     })
//                     .catch(function (reason) {
//                     });
//             }
//         },
//         // 数据渲染的时候执行获取购物车数据的方法。
//         mounted: function () {
//             this.getCart();
//         }
//     });
// }