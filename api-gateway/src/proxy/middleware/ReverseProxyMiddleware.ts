import { Injectable, NestMiddleware } from '@nestjs/common';
import { Request, Response } from 'express';
import {
  Options,
  RequestHandler,
  createProxyMiddleware,
} from 'http-proxy-middleware';

@Injectable()
export class ReverseProxyMiddleware implements NestMiddleware {
  static proxy: RequestHandler;

  static register(options: Options) {
    this.proxy = createProxyMiddleware(options);
    return this;
  }

  use(req: Request, res: Response, next: (error?: any) => void) {
    return ReverseProxyMiddleware.proxy(req, res, next);
  }
}
