import {FC} from 'react'

const FileIcon: FC<SvgProps> = (props) => {
  const {width = 12, height = 12, fill = '#1C274C'} = props
  return (
    <svg
      fill={fill}
      width={width + 'px'}
      height={height + 'px'}
      viewBox="0 0 32 32"
      data-name="Layer 1"
      id="Layer_1"
      xmlns="http://www.w3.org/2000/svg"
    >
      <rect height="1" width="9" x="13" y="2" />
      <rect height="1" width="12" x="10" y="29" />
      <rect
        height="1"
        transform="translate(-11 24) rotate(-90)"
        width="17"
        x="-2"
        y="17"
      />
      <rect
        height="1"
        transform="translate(9.5 41.5) rotate(-90)"
        width="20"
        x="15.5"
        y="15.5"
      />
      <rect height="1" width="7" x="6" y="8" />
      <rect height="1" width="8" x="15" y="5" />
      <rect height="1" width="8" x="15" y="8" />
      <rect height="1" width="14" x="9" y="11" />
      <rect height="1" width="14" x="9" y="14" />
      <rect height="1" width="14" x="9" y="17" />
      <rect height="1" width="14" x="9" y="20" />
      <rect height="1" width="14" x="9" y="23" />
      <rect height="1" width="14" x="9" y="26" />
      <rect
        height="1"
        transform="translate(-1.05 8.18) rotate(-45)"
        width="8.49"
        x="5.11"
        y="4.85"
      />
      <rect
        height="1"
        transform="translate(7 18) rotate(-90)"
        width="7"
        x="9"
        y="5"
      />
      <path d="M22,2V3h2a1,1,0,0,1,1,1V6h1V4a2,2,0,0,0-2-2Z" />
      <path d="M22,30V29h2a1,1,0,0,0,1-1V26h1v2a2,2,0,0,1-2,2Z" />
      <path d="M10,30V29H8a1,1,0,0,1-1-1V26H6v2a2,2,0,0,0,2,2Z" />
    </svg>
  )
}

export default FileIcon
