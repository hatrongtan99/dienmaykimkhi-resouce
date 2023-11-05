import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ConfigService } from '@nestjs/config';
import { createProxyMiddleware } from 'http-proxy-middleware';

interface RouteGateway {
  id: string;
  uri: string;
  predicates: string[];
  pathRewrite?: string[];
}

async function bootstrap() {
  const app = await NestFactory.create(AppModule, { bodyParser: false });

  const config = app.get<ConfigService>(ConfigService);

  const gatewayConfig = config.get<RouteGateway[]>('gateway.routes');

  gatewayConfig.forEach((gateway) => {
    const pathRewrite: Record<string, string> = gateway.pathRewrite?.reduce(
      (acc, curr) => {
        const [key, value = ''] = curr.split(', ');
        return { ...acc, [key]: value };
      },
      {},
    );

    app.use(
      createProxyMiddleware([...gateway.predicates], {
        target: gateway.uri,
        changeOrigin: true,
        pathRewrite,
      }),
    );
  });

  await app.listen(8080);
}
bootstrap();
