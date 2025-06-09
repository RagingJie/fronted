// publicPath: '/index'

module.exports = {
	// 在npm run build 或 yarn build 时 ，生成文件的目录名称（要和baseUrl的生产环境路径一致）（默认dist）
	outputDir: 'dist',
	// webpack-dev-server 相关配置
	devServer: {
		// 允许所有的主机
		allowedHosts: 'all',
		host: '0.0.0.0',
		port: 8048,
		open: true,
		hot: true,
		proxy: {
			'/kd-advertisement': {
				target: 'http://127.0.0.1:8888',
				changeOrigin: true
			},
		},
		client: {
			webSocketURL: 'wss://0.0.0.0:8080/ws',
		}
	},
}