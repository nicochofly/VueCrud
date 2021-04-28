import Vue from 'vue'
import Router from 'vue-router'
// import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'
import Home from '../components/Home'
import LibraryIndex from "@/components/library/LibraryIndex";
// import AboutIndex from "@/components/about/AboutIndex";
import ChanelInfo from "@/components/ChanelInfo";
import ChanelList from "@/components/ChanelList";


Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/home',
            name: 'Home',
            component: Home,
            // home页面并不需要被访问
            redirect: '/index',
            children: [
                {
                    path: '/index',
                    name: 'List',
                    component: ChanelList,
                    meta: {
                        requireAuth: true
                    }
                    // name: 'AppIndex',
                    // component: AppIndex,
                    // meta: {
                    //     requireAuth: true
                    // }
                },
                {
                    path: '/library',
                    name: 'Library',
                    component: LibraryIndex,
                    meta: {
                        requireAuth: true
                    }
                }
                ,
                // {
                //     path: '/about',
                //     name: 'About',
                //     component: AboutIndex,
                //     meta: {
                //         requireAuth: true
                //     }
                // }
                // ,
                {
                    path: '/info',
                    name: 'Info',
                    component: ChanelInfo,
                    meta: {
                        requireAuth: true
                    }
                }
                // ,
                // {
                //     path: '/list',
                //     name: 'List',
                //     component: ChanelList,
                //     meta: {
                //         requireAuth: true
                //     }
                // }
            ]
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        /*{
            path: '/index',
            name: 'AppIndex',
            component: AppIndex,
            meta: {
                requireAuth: true
            }
        }*/
    ]
})

