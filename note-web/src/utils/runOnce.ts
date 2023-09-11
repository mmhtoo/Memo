import {useEffect} from 'react'

const runOnce = (callback: () => void) => {
  useEffect(() => {
    callback()
  }, [])
}

export default runOnce
