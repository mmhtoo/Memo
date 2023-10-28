/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_PREFIX: string
  readonly VITE_REQUEST_TIMEOUT: number
  // more env variables...
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
