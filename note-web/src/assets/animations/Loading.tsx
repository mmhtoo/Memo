import {FC} from 'react'
import Lottie from 'lottie-react'
import loadingData from '@assets/lotties/loading.json'

type Props = {
  width?: number
  height?: number
  color?: string
}

const LoadingAnimation: FC<Props> = ({width = 24, height = 24, color}) => {
  return (
    <div
      style={{
        width: `${width}px`,
        height: `${height}px`,
      }}
    >
      <Lottie animationData={loadingData} loop={true} color={color} />
    </div>
  )
}

export default LoadingAnimation
