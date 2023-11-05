import { readFileSync } from 'fs';
import * as yaml from 'js-yaml';
import * as path from 'path';

export default () => {
  return yaml.load(
    readFileSync(path.join(__dirname, '../../', 'application.yml'), 'utf8'),
  ) as Record<string, any>;
};
