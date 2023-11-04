import {FC} from 'react'
import logo from '../../assets/imgs/MemoLogo.png'

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
