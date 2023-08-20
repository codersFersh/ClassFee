import router from '@/router';
import axios from 'axios'
import { Message } from 'element-ui';
import qs from 'qs'



// axios.defaults.timeout = 5000
// const baseURL = 'http://localhost:8089/'
const baseURL = process.env.VUE_APP_BASE_API
    // const baseURL='http://120.79.66.82:5201/'

/* global ctxPaths */
// request 拦截器
axios.interceptors.request.use(
        config => {
            config.headers.set("token", window.localStorage.getItem("token"));
            return config
        },
        error => {
            return Promise.reject(error)
        }
    )
    // reponse 拦截器
axios.interceptors.response.use(
        response => {
            const { data } = response
            return data;
        },
        error => {
            if (error.response) {
                switch (error.response.status) {
                    case 401:
                        localStorage.removeItem('token');
                        const { status, data } = response
                        const { message } = data
                        Message.error(message)
                        router.push({ name: 'Login' })
                }
            }
        }
    )
    // axios.interceptors.response.use(
    //     response => {
    //         if (response.data.status === 401) {
    //             alert('登录信息过期，请重新登录')
    //             window.location.href = '/'
    //         }
    //         return Promise.resolve(response.data)
    //     },
    //     error => {
    //         return Promise.reject(error)
    //     }
    // )



export const POST = (url, params) => {
    return axios.post(`${baseURL}${url}`, qs.stringify(params), {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Requested-With': 'XMLHttpRequest'
        }
    }).then(res => res)
}

export const POST2 = (url, params) => {
    return axios.post(`${baseURL}${url}`, params, {
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        }
    }).then(res => res)
}

export const GET = (url, params) => {
    return axios.get(`${baseURL}${url}`, { params: params }).then(res => res)
}

export const PUT = (url, params) => {
    return axios.put(`${baseURL}${url}`, params).then(res => res)
}

export const DELETE = (url, params) => {
    return axios.delete(`${baseURL}${url}`, { params: params }).then(res => res)
}

export const PATCH = (url, params) => {
    return axios.patch(`${baseURL}${url}`, params).then(res => res)
}