import {FC} from 'react'
import logo from '@assets/images/MemoLogo.png'

const LogoIcon: FC<{style?: {}}> = ({style = {}}) => {
  return (
    <img
      src={logo}
      style={{
        width: '80px',
        ...style,
      }}
    />
  )
}

export default LogoIcon
