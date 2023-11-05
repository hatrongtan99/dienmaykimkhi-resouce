import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import configuration from './config/configuration';

let ENV_FILE = '.env.development';
if (process.env.NODE_ENV === 'production') ENV_FILE = '.env.production';
@Module({
  imports: [
    ConfigModule.forRoot({
      load: [configuration],
      isGlobal: true,
      envFilePath: [ENV_FILE],
    }),
  ],
})
export class AppModule {}
