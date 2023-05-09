import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import PizzaOrderForm from '../components/PizzaOrderForm.vue'
import SpecialtyPizzaList from '../components/SpecialtyPizzaList.vue'
import OrderCart from '../views/OrderCart.vue'
import OrderScreen from '../views/OrderScreen.vue'
import Checkout from '../views/Checkout.vue'
import OrderComplete from '../views/OrderComplete.vue'
import OrderStatus from '../views/OrderStatus.vue'
import AddSpecialtyPizzaForm from '../components/AddSpecialtyPizzaForm.vue'
import ModifyPizzaOptionsForm from '../components/ModifyPizzaOptionsForm.vue'
import EmployeeMainPage from "../views/EmployeeMainPage.vue"
import CustomerMainPage from "../views/CustomerMainPage.vue"
import AddPizzaToppingForm from '../components/AddPizzaToppingForm.vue'
import AddPizzaCrustForm from '../components/AddPizzaCrustForm.vue'
import AddPizzaSauceForm from '../components/AddPizzaSauceForm.vue'
import AddPizzaSizeForm from '../components/AddPizzaSizeForm.vue'
import AddPizzaPremiumToppingForm from '../components/AddPizzaPremiumToppingForm.vue'
import SpecialtyPizzaOrderForm from '../components/SpecialtyPizzaOrderForm.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/menu',
      name: 'menu',
      component: OrderScreen,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    
    // TODO: need to be evaluated with team, used right now for testing
    {
      path: "/pizza-order-form/:pizzaName",
      name: "pizza-order-form",
      component: PizzaOrderForm,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/specialty-pizza-order-form/:pizzaName",
      name: "specialty-pizza-order-form",
      component: SpecialtyPizzaOrderForm,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/specialty-pizza-list",
      name: "specialty-pizza-list",
      component: SpecialtyPizzaList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/order-cart",
      name: "order-cart",
      component: OrderCart,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/checkout",
      name: "checkout",
      component: Checkout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/order-complete",
      name: "order-complete",
      component: OrderComplete,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/orders",
      name: "view-orders",
      component: OrderStatus,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/orders/:status",
      name: "view-orders-by-status",
      component: OrderStatus,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/specialty-pizza-list/add-pizza",
      name: "add-specialty-pizza-form",
      component: AddSpecialtyPizzaForm,
      meta: {
        requiresAuth: true
      }

    },
    {
      path: "/employee-main-page",
      name: "employee-main-page",
      component: EmployeeMainPage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/customer-main-page",
      name: "customer-main-page",
      component: CustomerMainPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/menu/modify-options",
      name: "modify-pizza-options-form",
      component: ModifyPizzaOptionsForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/menu/modify-options/add-topping",
      name: "add-pizza-topping-form",
      component: AddPizzaToppingForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/menu/modify-options/add-premium-topping",
      name: "add-pizza-premium-topping-form",
      component: AddPizzaPremiumToppingForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/menu/modify-options/add-sauce",
      name: "add-pizza-sauce-form",
      component: AddPizzaSauceForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/menu/modify-options/add-crust",
      name: "add-pizza-crust-form",
      component: AddPizzaCrustForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/menu/modify-options/add-size",
      name: "add-pizza-size-form",
      component: AddPizzaSizeForm,
      meta: {
        requiresAuth: true
      }
    },   
    
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
