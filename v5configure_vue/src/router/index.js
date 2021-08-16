import Vue from 'vue'
import Router from 'vue-router'
// import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'
import Home from '../components/Home'
// import LibraryIndex from "@/components/library/LibraryIndex";
// import AboutIndex from "@/components/about/AboutIndex";
// import ChanelInfo from "@/components/ChanelInfo";
import DeviceList from "@/components/DeviceList";
import ContentList from "@/components/ContentList";
import DeviceGroupList from "@/components/DeviceGroupList";
import CategoryList from "@/components/CategoryList";
// import Books from "@/components/Books";
import melemo_home from "@/components/kids_cloth/melemo_home";
import melemo_all from "@/components/kids_cloth/melemo_all";


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
                    component: DeviceList,
                    meta: {
                        requireAuth: true
                    }
                    // name: 'AppIndex',
                    // component: AppIndex,
                    // meta: {
                    //     requireAuth: true
                    // }
                },
             /*   {
                    path: '/library',
                    name: 'Library',
                    component: LibraryIndex,
                    meta: {
                        requireAuth: true
                    }
                }*/
                {
                    path: '/melemohome',
                    name: 'Melemo',
                    component: melemo_home,
                    meta: {
                        requireAuth: true
                    }
                }
                ,
                {
                    path: '/contents',
                    name: 'Contents',
                    component: ContentList,
                    meta: {
                        requireAuth: true
                    }
                }
                , {
                    path: '/devicegroups',
                    name: 'DeviceGroups',
                    component: DeviceGroupList,
                    meta: {
                        requireAuth: true
                    }
                }
                ,
                {
                    path: '/category',
                    name: 'Category',
                    component: CategoryList,
                    meta: {
                        requireAuth: true
                    }
                },
                {
                    path: '/melemoall',
                    name: 'MelemoAll',
                    component: melemo_all,
                    meta: {
                        requireAuth: true
                    }
                }
                // ,
                // {
                //     path: '/info',
                //     name: 'Info',
                //     component: ChanelInfo,
                //     meta: {
                //         requireAuth: true
                //     }
                // }
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

