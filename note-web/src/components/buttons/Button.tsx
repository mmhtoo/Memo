import {FC} from 'react'
import './buttonStyle.css'

type Props = {
  text: string
  onClick: () => void
  style?: {}
}

const BaseButton: FC<Props> = ({text = 'Click', onClick, style}) => {
  return (
    <button className={'btn'} style={style} onClick={onClick}>
      {text}
    </button>
  )
}

export default BaseButton
