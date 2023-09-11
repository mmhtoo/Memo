import {FC, PropsWithChildren} from 'react'
import {motion, AnimatePresence} from 'framer-motion'

const FadeIn: FC<PropsWithChildren> = ({children}) => {
  return (
    <AnimatePresence>
      <motion.div
        initial={{
          scale: 0,
          opacity: 0,
        }}
        animate={{
          scale: 1,
          opacity: 1,
        }}
        exit={{
          scale: 0,
          opacity: 0,
        }}
      >
        {children}
      </motion.div>
    </AnimatePresence>
  )
}

export default FadeIn
