import React, {FC} from 'react'
import './menuPopup.css'
import {HEIGHT, WIDTH} from '@constants/sharedDatas.ts'

type Props = {
  show: boolean
  position: {
    offsetX: number
    offsetY: number
  }
  onClickNewFolder: () => void
  onClickNewFile: () => void
}

const MenuPopup: FC<Props> = ({show, position, ...props}) => {
  const computedOffsetX =
    WIDTH - position.offsetX < 100 ? position.offsetX - 100 : position.offsetX
  const computedOffsetY =
    HEIGHT - position.offsetY < 100
      ? position.offsetY - 50 - 100
      : position.offsetY - 50

  const onClickItem = (
    e: React.MouseEvent<HTMLSpanElement, MouseEvent>,
    callback: () => void
  ) => {
    e.preventDefault()
    callback()
  }

  return (
    <div
      id="menu-popup"
      className={show ? 'show' : ''}
      style={{
        padding: '8px',
        borderRadius: '8px',
        backgroundColor: 'var(--sky-blue)',
        top: `${computedOffsetY}px`,
        left: `${computedOffsetX}px`,
      }}
    >
      <span
        id="menu-item"
        onClick={(e) => onClickItem(e, props.onClickNewFolder)}
      >
        + New Folder
      </span>
      <span
        id="menu-item"
        onClick={(e) => onClickItem(e, props.onClickNewFile)}
      >
        + New Note
      </span>
    </div>
  )
}

export default MenuPopup
