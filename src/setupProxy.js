const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/', // 프록시할 요청 경로
    createProxyMiddleware({
      target: 'https://port-0-jarame2024-dj712lls8pgqj9.sel5.cloudtype.app', // 백엔드 서버 주소
      changeOrigin: true,
    })
  );
};