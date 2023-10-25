import {HEIGHT} from '@constants/sharedDatas.ts'
import {FC} from 'react'
import LogoIcon from './LogoIcon'

const Header: FC = () => {
  return (
    <header
      className="px-5 sticky-top bg-sky-blue d-flex justify-content-between align-items-center"
      style={{
        height: `${HEIGHT * 0.08}px`,
        borderBottomWidth: '0.5px',
        borderBottomStyle: 'solid',
        borderBottomColor: 'ButtonShadow',
      }}
    >
      <LogoIcon />
    </header>
  )
}

export default Header
