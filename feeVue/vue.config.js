const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true, //是否丢依赖进行转义
    lintOnSave: false, //设置是否在开发环境下每次保存代码时都启用 eslint验证
    devServer: { //开发服务器的配置
        port: 8081, // 设置端口号
        proxy: { //服务器代理配置
            '/api': {
                target: 'http://192.168.1.6:8089', //后端地址
                changeOrigin: true,
                pathRewrite: {
                    '^/api': 'api'
                }
            }
        }
    }
})