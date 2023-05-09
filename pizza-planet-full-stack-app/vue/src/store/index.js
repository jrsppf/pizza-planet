import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
// import image1 from "../../public/images/pizza1.png"
// import image2 from "../../public/images/pizza2.png"
// import image3 from "../../public/images/pizza3.png"
// import image4 from "../../public/images/pizza4.png"
// import image5 from "../../public/images/pizza5.png"
// import image6 from "../../public/images/pizza6.png"


Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    //SETUP FOR CHECKOUT CART/ ORDER TO BE POSTED
    order: {
      orderId: '',
      status: '',
      total: '',
      address: 
        {
          name: '',
          street: '',
          city: '',
          state: '',
          zip: '',
        },
      email:"",
      cardInfo: {
        name: '',
        cardNumber: '',
        expirationDate: '',
        securityCode: '',
        billingZip: '',
      },
      phone: '',
      pizzas: [],
      // nonPizzaItems: 
      

      //REMOVE nonPizzaItems wrapper
      appetizers: [],
      salads: [],
      sides: [],
      drinks: []
      // name: '',
      // delivery: false,
    },
    //ID FOR POSTED ORDER - FOR ORDER CONFIRMATION PAGE
    confirmationID:0,
    //STATUS
    status: [
      {id:1,
      status:'PENDING'},
      {id:2,
      status: 'READY'},
      {id:3,
      status:'COMPLETE'},
      {id:4,
      status: 'CANCELLED'}],

    //ORDER BY STATUS LIST
    allOrders: [
      {
        orderId: 1,
        status: "PENDING",
        total: 40.00,
        address: "66 N. Hillcrest Rd.",
        cardInfo: "5555-5555-5555-5555",
        phone: "656-545-5454",
        pizzas: [
            {
                pizzaId: 1,
                size: "LARGE",
                crust: "DOUBLE STUFFED",
                sauce: "MARINARA",
                toppings: [
                    "MUSHROOMS",
                    "PEPPERONI"
                ],
                premiumToppings: [
                    "PINEAPPLE & HAM",
                ],
                price: 14.00
            },
            {
                pizzaId: 2,
                size: "SMALL",
                crust: "DOUBLE STUFFED",
                sauce: "PLAIN",
                toppings: [
                    "MUSHROOMS",
                    "ONION"
                ],
                premiumToppings: [
                ],
                price: 15.50
            }
        ],
        appetizers: [
            {
                itemId: 3,
                itemName: "CALAMARI",
                price: 6.50
            }
        ],
        salads: [
        ],
        sides: [
        ],
        drinks: [
        ]
    },
    {
      orderId: 2,
      status: "PENDING",
      total: 40.00,
      address: "66 N. Hillcrest Rd.",
      cardInfo: "5555-5555-5555-5555",
      phone: "656-545-5454",
      pizzas: [
          {
              pizzaId: 1,
              size: "LARGE",
              crust: "DOUBLE STUFFED",
              sauce: "MARINARA",
              toppings: [
                  "MUSHROOMS",
                  "PEPPERONI"
              ],
              premiumToppings: [
                  "PINEAPPLE & HAM",
              ],
              price: 14.00
          },
          {
              pizzaId: 2,
              size: "SMALL",
              crust: "DOUBLE STUFFED",
              sauce: "PLAIN",
              toppings: [
                  "MUSHROOMS",
                  "ONION"
              ],
              premiumToppings: [
              ],
              price: 35.50
          }
      ],
      appetizers: [],
      salads: [],
      sides: [],
      drinks: []
  }
    ],

    //PIZZA MENU
    pizzaMenu: [
      // {
      //   id: 1,
      //   image: 'pizza1.png',
      //   name: "Specialty Pizza 1",
      //   price: 14.00,
      //   isActive: true,
      // },
      // {
      //   id: 2,
      //   image: 'pizza2.png',
      //   name: "Specialty Pizza 2",
      //   price: 14.00,
      //   isActive: true,
      // },
      // {
      //   id: 3,
      //   image: 'pizza3.png',
      //   name: "Specialty Pizza 3",
      //   price: 14.00,
      //   isActive: true,
      // },
      // {
      //   id: 4,
      //   image: 'pizza4.png',
      //   name: "Specialty Pizza 4",
      //   price: 14.00,
      //   isActive: true,
      // },
      // {
      //   id: 5,
      //   image: 'pizza5.png',
      //   name: "Specialty Pizza 5",
      //   price: 14.00,
      //   isActive: true,
      // },
      // {
      //   id: 6,
      //   image: 'pizza6.png',
      //   name: "Specialty Pizza 6",
      //   price: 14.00,
      //   isActive: true,
      // },
      // {
      //   id: 7,
      //   image: 'pizza3.png',
      //   name: "Make your own",
      //   price: 12.00,
      //   link: "PizzaOrderForm",
      //   isActive: true,
      // }
    ],
    toppings: [
      // {
      //   toppingId: 1,
      //   name: "Black Olives",
      //   isPremium: false,
      //   price: 1.00,
      //   availability: "UNAVAILABLE",
      // },
      // {
      //   toppingId: 2,
      //   name: "Mushrooms",
      //   isPremium: false,
      //   price: 1.00,
      //   availability: "IN STOCK"
      // },
      // {
      //   toppingId: 3,
      //   name: "Onions",
      //   isPremium: false,
      //   price: 1.00,
      //   availability: "IN STOCK"
      // },
      // {
      //   toppingId: 4,
      //   name: "Pepperoni",
      //   isPremium: false,
      //   price: 1.00,
      //   availability: "IN STOCK",
      // },
      // {
      //   toppingId: 5,
      //   name: "Peppers",
      //   isPremium: false,
      //   price: 1.00,
      //   availability: "IN STOCK"
      // },
    ],
    premiumToppings: [
      // {
      //   toppingId: 1,
      //   name: "Bacon",
      //   isPremium: true,
      //   price: 2.00,
      //   availability: "IN STOCK",
      // },
      // {
      //   toppingId: 2,
      //   name: "Chicken",
      //   isPremium: true,
      //   price: 2.00,
      //   availability: "UNAVAILABLE"
      // },
      // {
      //   toppingId: 3,
      //   name: "Extra Cheese",
      //   isPremium: true,
      //   price: 2.00,
      //   availability: "IN STOCK"
      // },
      // {
      //   toppingId: 4,
      //   name: "Ham & Pineapple",
      //   isPremium: true,
      //   price: 2.00,
      //   availability: "IN STOCK",
      // },
      // {
      //   toppingId: 5,
      //   name: "Sausage",
      //   isPremium: true,
      //   price: 2.00,
      //   availability: "IN STOCK",
      // },
    ],
    sauce: [
      // {
      //   id: 1,
      //   name: "Red",
      //   price: 0.00,
      //   availability: "LOW STOCK"
      // },
      // {
      //   id: 2,
      //   name: "White",
      //   price: 2.00,
      //   availability: "IN STOCK"
      // },
      // {
      //   id: 3,
      //   name: "Marinara",
      //   price: 2.00,
      //   availability: "IN STOCK"
      // },
    ],
    crust: [
      // {
      //   id: 1,
      //   name: "Regular",
      //   price: 0.00,
      //   availability: "IN STOCK"
      // },
      // {
      //   id: 2,
      //   name: "Thin",
      //   price: 1.00,
      //   availability: "IN STOCK"
      // },
      // {
      //   id: 3,
      //   name: "Double Stuffed",
      //   price: 2.00,
      //   availability: "IN STOCK"
      // },
    ],
    size: [
      // {
      //   id: 1,
      //   name: "Small",
      //   price: 0.00
      // },
      // {
      //   id: 2,
      //   name: "Medium",
      //   price: 2.00
      // },
      // {
      //   id: 3,
      //   name: "Large",
      //   price: 4.00
      // },
    ],

    //NON-PIZZA MENU
    appetizers: [
      {
        "itemId": 1,
        "itemName": "Mozzarella sticks",
        "itemType": "APPETIZER",
        "itemPrice": 4.00,
        "itemAvailability": "IN STOCK",
        "imagePath": "pizza3.png"
      }
    ],
    salads: [
      {
      "itemId": 4,
      "itemName": "Cobb",
      "itemType": "SALAD",
      "itemPrice": 2.50,
      "itemAvailability": "IN STOCK",
      "imagePath": "pizza3.png"}
    ],
    sides: [
      {"itemId": 5,
      "itemName": "French fries",
      "itemType": "SIDE",
      "itemPrice": 4.50,
      "itemAvailability": "IN STOCK",
      "imagePath": "pizza3.png"}
    ],
    drinks: [
      {"itemId": 8,
      "itemName": "Dr. Pepper",
      "itemType": "DRINK",
      "itemPrice": 1.50,
      "itemAvailability": "IN STOCK",
      "imagePath": "pizza3.png"}
    ]
    },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_PIZZA_MENU(state, data){
      state.pizzaMenu = data;
      //Make Your Own is the first on Specialty Menu
      // const makeYourOwnPizza = {
      //   id: 7,
      //   image: 'pizza1.png',
      //   name: "Make your own",
      //   price: 12.00,
      //   link: "PizzaOrderForm"
      // }
      // state.pizzaMenu.push(makeYourOwnPizza);
    },
    SET_TOPPINGS_MENU(state, data){
      state.toppings = data;
    },
    SET_PREMIUMTOPPINGS_MENU(state, data){
      state.premiumToppings = data;
    },
    SET_SAUCE_MENU(state, data){
      state.sauce = data;
    },
    SET_CRUST_MENU(state, data){
      state.crust = data;
    },
    SET_SIZE_MENU(state, data){
      state.size = data;
    },
    SET_APPETIZER_MENU(state, data){
      state.appetizers = data;
    },
    SET_SALAD_MENU(state, data){
      state.salads = data;
    },
    SET_SIDE_MENU(state, data){
      state.sides = data;
    },
    SET_DRINK_MENU(state, data){
      state.drinks = data;
    },
    SET_ORDER_LIST(state, data){
      state.allOrders = data;
    },
    ADD_PIZZA_TO_ORDER(state, orderItem) {
      state.order.pizzas.push(orderItem)
    },

    ADD_APPETIZER_TO_ORDER(state, orderItem) {
      // state.order.nonPizzaItems.appetizers.push(orderItem)
      state.order.appetizers.push(orderItem)
    },
    ADD_SIDES_TO_ORDER(state, orderItem) {
      // state.order.nonPizzaItems.sides.push(orderItem)
      state.order.sides.push(orderItem)
    },
    ADD_SALADS_TO_ORDER(state, orderItem) {
      // state.order.nonPizzaItems.salads.push(orderItem)
      state.order.salads.push(orderItem)
    },
    ADD_DRINKS_TO_ORDER(state, orderItem) {
      // state.order.nonPizzaItems.drinks.push(orderItem)
      state.order.drinks.push(orderItem)
    },
    SET_ADDRESS(state, deliveryAddress) {
      state.order.address = deliveryAddress
    },
    SET_PAYMENT(state, paymentInfo) {
      state.order.cardInfo = paymentInfo;
    },
    SET_ORDER_STATUS_TO_PENDING(state, order) {
      order.status = "PENDING";
    },
    SET_ORDER_TOTAL(state, total) {
      state.order.total = total
    },
    // SET_CUSTOMER_NAME(state, name) {
    //   state.order.name = name;
    // },
    // SET_DELIVERY(state) {
    //   state.order.delivery = true;
    // } ,
    DELETE_PIZZA(state, pizzaToRemove) {
      state.order.pizzas = state.order.pizzas.filter(pizza => pizza !== pizzaToRemove)
    },
    DELETE_NON_PIZZA(state, nonPizzaToRemove) {
      let type = nonPizzaToRemove.itemType;
      if(type=="APPETIZER") {
        state.order.nonPizzaItems.appetizers = state.order.nonPizzaItems.appetizers.filter(appetizer => appetizer!=nonPizzaToRemove);
      } else if(type=="SALAD") {
        state.order.nonPizzaItems.salads = state.order.nonPizzaItems.salads.filter(salad => salad != nonPizzaToRemove);
      } else if(type=="SIDE") {
        state.order.nonPizzaItems.sides = state.order.nonPizzaItems.sides.filter(side => side != nonPizzaToRemove);
      } else if(type=="DRINK") {
        state.order.nonPizzaItems.drinks = state.order.nonPizzaItems.drinks.filter(drink => drink != nonPizzaToRemove);
      }
    },
    SAVE_PREMIUM_TOPPING(state, newTopping) {
      state.toppings.push(newTopping)
    },
    SAVE_NON_PREMIUM_TOPPING(state, newTopping) {
      state.premiumToppings.push(newTopping)
    },

  }
})
