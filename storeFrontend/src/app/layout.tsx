import Footer from '@/components/layout/footer/Footer'
import Header from '@/components/layout/header/Header'

import './globals.css'
import { Inter } from 'next/font/google'
import NavTop from '@/components/layout/navbar/NavTop'
import Providers from './Providers'

const inter = Inter({ subsets: ['latin'] })

export const metadata = {
  title: 'Điện Máy Kim Khí',
  description: '',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={`${inter.className} text-text-color bg-stone-100`}>
        <Providers>
          <Header />
          <NavTop />
          <main>{children}</main>
          <Footer />
        </Providers>
      </body>
    </html>



  )
}
